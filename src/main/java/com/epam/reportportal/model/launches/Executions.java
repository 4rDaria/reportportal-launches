package com.epam.reportportal.model.launches;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Executions {
    @JsonProperty("total")
    private int total;
    @JsonProperty("passed")
    private int passed;
    @JsonProperty("failed")
    private int failed;
    @JsonProperty("skipped")
    private int skipped;

}
