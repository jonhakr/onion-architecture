package com.mycompany.actions.database;

import static java.util.Collections.emptyList;

import com.mycompany.data.Company;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.mycompany.data.Dataset;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.BeanMapper;

public class DatabaseClient {
  private final Jdbi jdbi;

  public DatabaseClient() {
    jdbi = Jdbi.create("jdbc:sqlite:database.db");
    jdbi.useTransaction(
        transaction -> {
          transaction.execute("create table if not exists dataset(timestamp)");
          transaction.execute(
              "create table if not exists company (dataset_id references dataset(ROWID), symbol, companyName, marketCap, sector, industry, beta, price, lastAnnualDividend, volume, exchange, exchangeShortName, country, isEtf, isActivelyTrading, earningsPerShare, bookValuePerShare, salesPerShare)");
        });
  }

  public int saveDataset(List<Company> companies) {
    return jdbi.inTransaction(
        transaction -> {
          var now = Instant.now();
          var dataset =
              transaction
                  .createUpdate("insert into dataset values(:timestamp)")
                  .bind("timestamp", Timestamp.from(now))
                  .executeAndReturnGeneratedKeys()
                  .map(
                      (rs, ctx) -> new Dataset(rs.getInt("last_insert_rowid()"), now, emptyList()));
          var id = dataset.one().getId();
          var batch =
              transaction.prepareBatch(
                  "insert into company values (:dataset_id, :symbol, :companyName, :marketCap, :sector, :industry, :beta, :price, :lastAnnualDividend, :volume, :exchange, :exchangeShortName, :country, :isEtf, :isActivelyTrading, :earningsPerShare, :bookValuePerShare, :salesPerShare)");
          companies.forEach(company -> batch.bind("dataset_id", id).bindBean(company).add());
          int[] counts = batch.execute();
          return id;
        });
  }

  public Optional<Dataset> getDataset(int id) {
    var query =
        "select * "
            + "from dataset "
            + "join company on company.dataset_id = dataset.rowid "
            + "where dataset.rowid = :id";
    return jdbi.withHandle(
        handle ->
            handle
                .createQuery(query)
                .bind("id", id)
                .registerRowMapper(BeanMapper.factory(Dataset.class))
                .registerRowMapper(BeanMapper.factory(Company.class))
                .reduceRows(
                    Optional.empty(),
                    (optionalDataset, rowView) -> {
                      var company = rowView.getRow(Company.class);
                      var companies =
                          optionalDataset.map(Dataset::getCompanies).orElse(emptyList());
                      return Optional.of(
                          new Dataset(
                              id,
                              rowView.getColumn("timestamp", Timestamp.class).toInstant(),
                              Stream.concat(companies.stream(), Stream.of(company))
                                  .collect(Collectors.toList())));
                    }));
  }

  public List<Integer> getDatasetIds() {
    var query = "select rowid from dataset";
    return jdbi.withHandle(handle -> handle.createQuery(query).mapTo(Integer.class).list());
  }
}
