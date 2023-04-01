package com.mycompany.calculations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.Instant;
import java.util.List;

public class DatasetMapper {
    private static final Gson gson;

    static {
        var gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Instant.class, new InstantTypeAdapter());
        gson = gsonBuilder.create();
    }
    public static String toResponse(List<String> stringList) {
        return gson.toJson(stringList);
    }
}
