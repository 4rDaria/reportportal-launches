package com.epam.reportportal.model.launches;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Attribute {

    @JsonProperty
    String key;
    @JsonProperty
    String value;
}
