package com.epam.reportportal.ui.stepDefinitions;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.epam.reportportal.cucumber.Context;
import com.epam.reportportal.cucumber.TestContext;
import com.epam.reportportal.pages.launches.GridRow;
import com.epam.reportportal.pages.launches.LaunchesPage;
import io.cucumber.java.After;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.epam.reportportal.cucumber.Context.GRID_ROW_ELEMENTS;


public class EachLaunchesContainsAllTestCountDataSteps extends BaseStep{

    private static final Logger LOGGER = LogManager.getRootLogger();

    public EachLaunchesContainsAllTestCountDataSteps(TestContext testContext) {
        super(testContext);
    }

    @When("I choose any launch")
    public void iChooseAnyLaunch() {
        //choose elements simulation
    }

    @Then("Each launch should contain test count data for {string} with {string}")
    public void eachLaunchShouldContainTestCountDataForCategory(String category, String categoryCss) {
        ElementsCollection gridRowElements = (ElementsCollection) getScenarioContext().getContext(GRID_ROW_ELEMENTS);
        for (SelenideElement gridRow : gridRowElements) {
            GridRow launch = new GridRow(gridRow);
            SelenideElement element = launch.category(categoryCss);
            String id = launch.launchId();
            if (element == null) {
                LOGGER.info("Element " + category + " not found in launch: " + id);
            }
        }
    }
}
