package com.epam.reportportal.ui.stepDefinitions;

import com.epam.reportportal.cucumber.ScenarioContext;
import com.epam.reportportal.cucumber.TestContext;
import lombok.Getter;

public class BaseStep {

    @Getter
    private ScenarioContext scenarioContext;

    @Getter
    private String baseURl;

    @Getter
    private String project;

    public BaseStep(TestContext testContext) {

        scenarioContext = testContext.getScenarioContext();
        baseURl = testContext.getBaseUrl();
        project = testContext.getProject();
    }

}
