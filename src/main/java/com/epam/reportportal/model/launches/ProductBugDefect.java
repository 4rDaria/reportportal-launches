package com.epam.reportportal.model.launches;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductBugDefect extends Defect{
    @JsonProperty("pb001")
    private int pb001;
}
