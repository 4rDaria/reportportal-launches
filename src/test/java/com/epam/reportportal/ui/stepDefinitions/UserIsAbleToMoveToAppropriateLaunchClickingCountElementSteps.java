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

import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.epam.reportportal.utils.configuration.EnvironmentConfiguration.baseUrlForCurrentEnv;
import static com.epam.reportportal.utils.configuration.EnvironmentConfiguration.projectNameForCurrentEnv;
import static org.junit.Assert.assertEquals;

public class UserIsAbleToMoveToAppropriateLaunchClickingCountElementSteps {

    private static final Logger LOGGER = LogManager.getRootLogger();
    public static final String BASE_URL = baseUrlForCurrentEnv();
    public static final String PROJECT = projectNameForCurrentEnv();
    private static LaunchesPage launchesPage;
    private ElementsCollection gridRowElements;

    private GridRow launch;
    private String launchId;

    @When("I choose count element")
    public void chooseCountElement(){
        ElementsCollection gridRowElements = launchesPage.gridRowElements();
        launch = new GridRow(gridRowElements.first());
        launchId = launch.launchId();
    }

    @Then("I am able to move to appropriate launch via {string} element of {string} with {string}")
    public void moveToAppropriateLaunchViaCountElement(String elementIdentificator, String category, String param){
        SelenideElement element = launch.categoryCount(elementIdentificator);

        if (!element.exists()) {
            LOGGER.info("Can't check moving to appropriate via " + elementIdentificator +
                    " because there no data in this category");
        } else {
            element.click();
            sleep(500);

            String expectedUrl = BASE_URL + "/ui/#" + PROJECT + "/launches/all/" + launchId +
                    "?item0Params=filter.eq.hasStats%3Dtrue%26filter.eq.hasChildren%3Dfalse%26filter."
                    + "in.type%3DSTEP%26filter.in.status%3D" + param;
            assertEquals(expectedUrl, getWebDriver().getCurrentUrl());
            back();
        }
    }

    @After()
    public void tearDownForUi() {
        WebDriverRunner.closeWebDriver();
    }
}

