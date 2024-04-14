package com.epam.reportportal.ui.stepDefinitions;

import com.epam.reportportal.model.user.User;
import com.epam.reportportal.pages.launches.LaunchesPage;
import com.epam.reportportal.services.UserCreator;
import io.cucumber.java.en.Given;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.epam.reportportal.services.Login.login;
import static com.epam.reportportal.services.Login.openLoginPage;
import static org.junit.Assert.assertNotNull;

public class CommonSteps {

    private static LaunchesPage launchesPage;

    @Given("I am logged in and on the launches page")
    public void iAmLoggedInAndOnTheLaunchesPage() {
        User testUser = UserCreator.withCredentialsFromProperty();
        openLoginPage();
        getWebDriver().manage().window().maximize();
        launchesPage = login(testUser);
    }

    @Given("I have a list of launch")
    public void iHaveAListOfLaunch() {
        assertNotNull("Grid row elements should not be null", launchesPage.gridRowElements());
    }
}
