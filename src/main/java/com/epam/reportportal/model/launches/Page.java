package com.epam.reportportal.model.launches;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Page {
    @JsonProperty
    int number;
    @JsonProperty
    int size;
    @JsonProperty
    int totalElements;
    @JsonProperty
    int totalPages;
}
