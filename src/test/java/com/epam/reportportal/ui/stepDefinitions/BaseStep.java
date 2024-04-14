package com.epam.reportportal.ui.stepDefinitions;

import com.epam.reportportal.cucumber.ScenarioContext;
import com.epam.reportportal.cucumber.TestContext;
import com.epam.reportportal.pages.launches.LaunchesPage;
import lombok.Getter;

public class BaseStep {

    //private EndPoints endPoints;
    //private LaunchesPage launchesPage;
    @Getter
    private ScenarioContext scenarioContext;

    @Getter
    private String baseURl;

    public BaseStep(TestContext testContext) {
        //endPoints = testContext.getEndPoints();
        scenarioContext = testContext.getScenarioContext();
        baseURl = testContext.getBaseURl();
    }

//    public EndPoints getEndPoints() {
//        return endPoints;
//    }

}
