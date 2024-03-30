package com.epam.reportportal.model.launches;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Statistics {
    @JsonProperty
    private Executions executions;
    @JsonProperty
    private Defects defects;
}
