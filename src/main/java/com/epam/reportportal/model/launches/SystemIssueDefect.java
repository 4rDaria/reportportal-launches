package com.epam.reportportal.model.launches;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SystemIssueDefect extends Defect{
    @JsonProperty("si001")
    private int si001;
}
