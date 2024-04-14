package com.epam.reportportal.ui.stepDefinitions;

import com.epam.reportportal.cucumber.Context;
import com.epam.reportportal.cucumber.TestContext;
import com.epam.reportportal.model.user.User;
import com.epam.reportportal.pages.launches.LaunchesPage;
import com.epam.reportportal.services.UserCreator;
import io.cucumber.java.en.Given;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.epam.reportportal.cucumber.Context.GRID_ROW_ELEMENTS;
import static com.epam.reportportal.cucumber.Context.LAUNCH_PAGE;
import static com.epam.reportportal.services.Login.login;
import static com.epam.reportportal.services.Login.openLoginPage;
import static org.junit.Assert.assertNotNull;

public class CommonSteps extends BaseStep {

    //private static LaunchesPage launchesPage;

    public CommonSteps(TestContext testContext) {
        super(testContext);
    }

    @Given("I am logged in and on the launches page")
    public void iAmLoggedInAndOnTheLaunchesPage() {
        User testUser = UserCreator.withCredentialsFromProperty();
        openLoginPage();
        getWebDriver().manage().window().maximize();
       // launchesPage = login(testUser);
       // launchesPage = login(testUser);
        getScenarioContext().setContext(LAUNCH_PAGE, login(testUser));
    }

    @Given("I have a list of launch")
    public void iHaveAListOfLaunch() {
        LaunchesPage launchesPage = (LaunchesPage) getScenarioContext().getContext(LAUNCH_PAGE);
        getScenarioContext().setContext(GRID_ROW_ELEMENTS, launchesPage.gridRowElements());
        assertNotNull("Grid row elements should not be null", getScenarioContext().getContext(GRID_ROW_ELEMENTS));
    }
}
