package com.epam.reportportal.model.launches;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Defects {
    @JsonProperty("system_issue")
    private SystemIssueDefect systemIssue;
    @JsonProperty("product_bug")
    private ProductBugDefect productBug;
    @JsonProperty("to_investigate")
    private ToInvestigateDefect toInvestigate;
    @JsonProperty("automation_bug")
    private AutomationBugDefect automationBug;
}
