package com.epam.reportportal.model.launches;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Page {
    @JsonProperty("number")
    int number;
    @JsonProperty("size")
    int size;
    @JsonProperty("totalElements")
    int totalElements;
    @JsonProperty("totalPages")
    int totalPages;
}
