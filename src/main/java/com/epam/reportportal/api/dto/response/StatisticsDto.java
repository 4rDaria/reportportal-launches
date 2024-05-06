package com.epam.reportportal.api.dto.response;

import com.epam.reportportal.model.launches.Defects;
import com.epam.reportportal.model.launches.Executions;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StatisticsDto implements Serializable {
    @JsonProperty
    private Executions executions;
    @JsonProperty
    private Defects defects;
}
