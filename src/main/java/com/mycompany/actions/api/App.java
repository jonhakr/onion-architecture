package com.mycompany.actions.api;

import static spark.Spark.get;
import static spark.Spark.post;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mycompany.calculations.DatasetCalculations;
import com.mycompany.calculations.DatasetMapper;
import com.mycompany.calculations.InstantTypeAdapter;
import com.mycompany.data.ValidationException;
import com.mycompany.actions.database.DatabaseClient;
import com.mycompany.data.Company;
import com.mycompany.actions.financial_api.FinancialAPIClient;
import com.mycompany.calculations.DatasetService;
import java.time.Instant;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
  private static final Logger logger = LoggerFactory.getLogger(App.class);

  private static final DatasetService datasetService;
  private static final Gson gson;
  private static final String json = "application/json";

  private static final FinancialAPIClient financialAPIClient;

  private static final DatabaseClient databaseClient;

  static {
    try {
      financialAPIClient = new FinancialAPIClient();
      databaseClient = new DatabaseClient();
      datasetService = new DatasetService(financialAPIClient, databaseClient);
    } catch (Exception e) {
      logger.atError().log("could not create dependencies: {}", e);
      throw new RuntimeException(e);
    }

    var gsonBuilder = new GsonBuilder();
    gsonBuilder.registerTypeAdapter(Instant.class, new InstantTypeAdapter());
    gson = gsonBuilder.create();
  }

  public static void main(String[] args) {
    get(
        "/ping",
        (req, res) -> {
          logger.atInfo().log("GET /ping");
          return "pong";
        });
    get(
        "/datasets/:id",
        (request, response) -> {
          var id = Integer.parseInt(request.params("id"));
          var opt = datasetService.getDataset(id);
          return opt.map(
                  src -> {
                    response.type(json);
                    return gson.toJson(src);
                  })
              .orElseGet(
                  () -> {
                    response.status(404);
                    return "dataset with id " + id + " does not exist";
                  });
        });
    get(
        "/datasets",
        (request, response) -> {
          var list = datasetService.getDatasetIds();
          response.type(json);
          return gson.toJson(list);
        });
    post(
        "/datasets",
        (request, response) -> {
          try {
            logger.atInfo().log("POST /datasets");
            var id = datasetService.newDataset(request.body());
            response.header("Location", "/datasets/" + id);
            response.status(201);
            return "";
          } catch (ValidationException e) {
            logger.atError().log("error: ", e);
            response.status(400);
            return e.getMessage();
          }
        });
    get(
        "/recommendation",
        (request, response) -> {
          logger.atInfo().log("GET /recommendation");
          var id = request.queryParams("dataset");
          var datasetId = DatasetCalculations.parseDatasetId(id);
          if (datasetId.isEmpty())
              return "invalid dataset id: " + id;
          var dataset = databaseClient.getDataset(datasetId.get());
          if (dataset.isEmpty())
            return "No dataset with id: " + id;

          var companySymbols = DatasetCalculations.top5CompaniesBySymbol(dataset.get());
          response.type(json);
          return DatasetMapper.toResponse(companySymbols);
        });
  }
}
