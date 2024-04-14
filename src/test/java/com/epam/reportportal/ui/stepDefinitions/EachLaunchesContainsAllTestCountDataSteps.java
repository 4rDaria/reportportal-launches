package com.epam.reportportal.ui.stepDefinitions;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.epam.reportportal.pages.launches.GridRow;
import com.epam.reportportal.pages.launches.LaunchesPage;
import io.cucumber.java.After;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class EachLaunchesContainsAllTestCountDataSteps {

    private static final Logger logger = LogManager.getRootLogger();
    private static LaunchesPage launchesPage;
    private ElementsCollection gridRowElements;

    @When("I choose any launch")
    public void iChooseAnyLaunch() {
        System.out.println("it is working");
    }

    @Then("Each launch should contain test count data for category")
    public void eachLaunchShouldContainTestCountDataForCategory() {
        gridRowElements = launchesPage.gridRowElements();
        for (SelenideElement gridRow : gridRowElements) {
            GridRow launch = new GridRow(gridRow);
            SelenideElement element = launch.category("total-col");
            String id = launch.launchId();
            if (element == null) {
                logger.info("Element " + "total-col" + " not found in launch: " + id);
            }
        }
    }

    @After()
    public void tearDownForUi() {
        WebDriverRunner.closeWebDriver();
    }
}
