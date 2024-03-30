package com.epam.reportportal.model.launches;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Executions {
    @JsonProperty
    private int total;
    @JsonProperty
    private int passed;
    @JsonProperty
    private int failed;
    @JsonProperty
    private int skipped;

}
