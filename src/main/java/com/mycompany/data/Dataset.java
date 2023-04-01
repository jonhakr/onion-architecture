package com.mycompany.data;

import java.time.Instant;
import java.util.List;

public class Dataset {
  private int id;
  private Instant timestamp;
  private List<Company> companies;

  public Dataset(int id, Instant timestamp, List<Company> companies) {
    this.id = id;
    this.timestamp = timestamp;
    this.companies = companies;
  }

  public List<Company> getCompanies() {
    return companies;
  }

  public void setCompanies(List<Company> companies) {
    this.companies = companies;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Instant getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Instant timestamp) {
    this.timestamp = timestamp;
  }
}
