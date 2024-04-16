package com.epam.reportportal.cucumber;

import lombok.Getter;

import static com.epam.reportportal.utils.configuration.EnvironmentConfiguration.baseUrlForCurrentEnv;
import static com.epam.reportportal.utils.configuration.EnvironmentConfiguration.projectNameForCurrentEnv;

public class TestContext {

    @Getter
    private ScenarioContext scenarioContext;

    @Getter
    private String baseUrl;

    @Getter
    private String project;

    public TestContext() {
        scenarioContext = new ScenarioContext();
        baseUrl = baseUrlForCurrentEnv();
        project = projectNameForCurrentEnv();
    }
}
