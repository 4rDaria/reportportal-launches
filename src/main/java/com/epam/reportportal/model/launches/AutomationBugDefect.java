package com.epam.reportportal.model.launches;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AutomationBugDefect extends Defect{
    @JsonProperty("ab001")
    private int ab001;
}
