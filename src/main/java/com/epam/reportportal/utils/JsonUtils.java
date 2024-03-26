package com.epam.reportportal.utils;

import com.epam.reportportal.model.launches.Launches;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Launches parseJsonToLaunches(String json) {
        try {
            return objectMapper.readValue(json, Launches.class);
        } catch (Exception e) {
            throw new RuntimeException("Error parsing JSON to Launches", e);
        }
    }

    public static String writeValueAsString(Object requestBodyMap) {
        try {
            return objectMapper.writeValueAsString(requestBodyMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Can't write value as string");
        }
    }


}
