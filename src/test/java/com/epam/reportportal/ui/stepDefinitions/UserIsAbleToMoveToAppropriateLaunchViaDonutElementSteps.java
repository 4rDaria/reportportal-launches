package com.epam.reportportal.ui.stepDefinitions;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.WebDriverRunner;
import com.epam.reportportal.pages.launches.GridRow;
import com.epam.reportportal.pages.launches.LaunchesPage;
import io.cucumber.java.After;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.epam.reportportal.constants.Constants.PROJECT;
import static org.junit.Assert.assertEquals;

public class UserIsAbleToMoveToAppropriateLaunchViaDonutElementSteps {

    private static final Logger LOGGER = LogManager.getRootLogger();
    private static LaunchesPage launchesPage;
    private ElementsCollection gridRowElements;

    private GridRow launch;
    private String launchId;

    @When("I choose donut element")
    public void iChooseDonutElement() {
        gridRowElements = launchesPage.gridRowElements();
        launch = new GridRow(gridRowElements.first());
        launchId = launch.launchId();
    }

    @Then("I am able to move to appropriate launch via {string} element of {string}")
    public void moveToAppropriateLaunchViaDonutElement(String elementIdentificator, String category) {
        if (!launch.donutElementByType(elementIdentificator).exists()) {
            LOGGER.info("Can't check moving to appropriate via " + category + " because there no data in this category");
        } else {
            launch.donutElementByType(elementIdentificator).click();
            sleep(500);

            String expectedUrl = baseUrl + "/ui/#" + PROJECT + "/launches/all/" + launchId +
                    "?item0Params=filter.eq.hasStats%3Dtrue%26filter.eq.hasChildren%3Dfalse%26filter.in.issueType%3D" +
                    elementIdentificator + "001";

            assertEquals(expectedUrl, getWebDriver().getCurrentUrl());
            back();
        }
    }

    @After()
    public void tearDownForUi() {
        WebDriverRunner.closeWebDriver();
    }
}
