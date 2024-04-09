package com.epam.reportportal.ui.stepDefinitions;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.epam.reportportal.model.user.User;
import com.epam.reportportal.pages.launches.GridRow;
import com.epam.reportportal.pages.launches.LaunchesPage;
import com.epam.reportportal.services.UserCreator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.epam.reportportal.services.Login.login;
import static com.epam.reportportal.services.Login.openLoginPage;

public class EachLaunchesContainsAllTestCountDataSteps {

    private static final Logger logger = LogManager.getRootLogger();
    private static LaunchesPage launchesPage;
    private ElementsCollection gridRowElements;

    @Given("I am logged in and on the launches page")
    public void iAmLoggedInAndOnTheLaunchesPage() {
        User testUser = UserCreator.adminUser();
        openLoginPage();
        getWebDriver().manage().window().maximize();
        launchesPage = login(testUser);
    }

    @Given("I have a list of launch")
    public void iHaveAListOfLaunch() {
        gridRowElements = launchesPage.gridRowElements();
    }

    @When("I choose any launch")
    public void iChooseAnyLaunch() {
        System.out.println("it is working");
    }

    @Then("Each launch should contain test count data for category")
    public void eachLaunchShouldContainTestCountDataForCategory() {
        for (SelenideElement gridRow : gridRowElements) {
            GridRow launch = new GridRow(gridRow);
            SelenideElement element = launch.category("total-col");
            String id = launch.launchId();
            if (element == null) {
                logger.info("Element " + "total-col" + " not found in launch: " + id);
            }
        }
    }
}
