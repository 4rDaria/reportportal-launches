package com.epam.reportportal.ui.stepDefinitions;

import com.epam.reportportal.cucumber.ScenarioContext;
import com.epam.reportportal.cucumber.TestContext;
import lombok.Getter;

@Getter
public class BaseStep {

    private ScenarioContext scenarioContext;

    private String baseURl;

    private String project;

    public BaseStep(TestContext testContext) {

        scenarioContext = testContext.getScenarioContext();
        baseURl = testContext.getBaseUrl();
        project = testContext.getProject();
    }

}
