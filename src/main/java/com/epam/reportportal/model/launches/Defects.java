package com.epam.reportportal.model.launches;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Defects {
    @JsonProperty("system_issue")
    private SystemIssueDefect system_issue;
    @JsonProperty("product_bug")
    private ProductBugDefect product_bug;
    @JsonProperty("to_investigate")
    private ToInvestigateDefect to_investigate;
    @JsonProperty("automation_bug")
    private AutomationBugDefect automation_bug;
}
