package com.epam.reportportal.model.launches;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ToInvestigateDefect extends Defect{
    @JsonProperty("ti001")
    private int ti001;
}
