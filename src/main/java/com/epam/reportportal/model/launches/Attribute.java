package com.epam.reportportal.model.launches;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Attribute {

    @JsonProperty("key")
    String key;
    @JsonProperty("value")
    String value;
}
