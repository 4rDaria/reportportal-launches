package com.epam.reportportal.ui.stepDefinitions;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.epam.reportportal.cucumber.TestContext;
import com.epam.reportportal.pages.launches.GridRow;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.epam.reportportal.cucumber.Context.*;
import static org.junit.Assert.assertEquals;

public class UserIsAbleToMoveToAppropriateLaunchClickingCountElementSteps extends BaseStep{

    private static final Logger LOGGER = LogManager.getRootLogger();

    public UserIsAbleToMoveToAppropriateLaunchClickingCountElementSteps(TestContext testContext) {
        super(testContext);
    }

    @When("I choose count element")
    public void chooseCountElement(){
        ElementsCollection gridRowElements = (ElementsCollection) getScenarioContext().getContext(GRID_ROW_ELEMENTS);
        getScenarioContext().setContext(LAUNCH, new GridRow(gridRowElements.first()));
        GridRow launch = (GridRow) getScenarioContext().getContext(LAUNCH);
        getScenarioContext().setContext(LAUNCH_ID, launch.launchId());
    }

    @Then("I am able to move to appropriate launch via {string} element of {string} with {string}")
    public void moveToAppropriateLaunchViaCountElement(String elementIdentificator, String category, String param){
        GridRow launch = (GridRow) getScenarioContext().getContext(LAUNCH);
        SelenideElement element = launch.categoryCount(elementIdentificator);

        if (!element.exists()) {
            LOGGER.info("Can't check moving to appropriate via " + elementIdentificator +
                    " because there no data in this category");
        } else {
            element.click();
            sleep(500);

            String baseUrl = getBaseURl();
            String project = getProject();
            String launchId = getScenarioContext().getContext(LAUNCH_ID).toString();

            String expectedUrl = baseUrl + "/ui/#" + project + "/launches/all/" + launchId +
                    "?item0Params=filter.eq.hasStats%3Dtrue%26filter.eq.hasChildren%3Dfalse%26filter."
                    + "in.type%3DSTEP%26filter.in.status%3D" + param;
            assertEquals(expectedUrl, getWebDriver().getCurrentUrl());
            back();
        }
    }
}

