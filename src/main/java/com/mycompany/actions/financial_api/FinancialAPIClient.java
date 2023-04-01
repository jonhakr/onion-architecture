package com.mycompany.actions.financial_api;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import com.mycompany.data.ValidationException;
import com.mycompany.data.Company;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class FinancialAPIClient {
  private static final String api = "https://larlonboubrsjpexqzzolyfl.z1.web.core.windows.net";

  private static final OkHttpClient client = new OkHttpClient();
  private static final Gson gson = new Gson();

  public List<String> symbols() throws IOException, ValidationException {
    var request = new Request.Builder().url(api + "/all.json").build();
    try (var response = client.newCall(request).execute()) {
      var body = response.body();
      if (body == null) {
        throw new ValidationException("body is `null`");
      }
      return List.of(gson.fromJson(body.string(), String[].class));
    }
  }

  public Optional<Company> financialData(String symbol) throws IOException {
    var request = new Request.Builder().url(api + "/" + symbol.toUpperCase() + ".json").build();
    try (var response = client.newCall(request).execute()) {
      var body = response.body();
      if (body == null || response.code() == 404) {
        return Optional.empty();
      } else {
        return Optional.ofNullable(gson.fromJson(body.string(), Company.class));
      }
    }
  }
}
