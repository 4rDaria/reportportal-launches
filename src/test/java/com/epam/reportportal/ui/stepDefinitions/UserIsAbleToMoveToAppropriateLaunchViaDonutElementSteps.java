package com.epam.reportportal.ui.stepDefinitions;

import com.codeborne.selenide.ElementsCollection;
import com.epam.reportportal.cucumber.TestContext;
import com.epam.reportportal.pages.launches.GridRow;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.epam.reportportal.cucumber.Context.*;import static org.junit.Assert.assertEquals;

public class UserIsAbleToMoveToAppropriateLaunchViaDonutElementSteps extends BaseStep{

    private static final Logger LOGGER = LogManager.getRootLogger();

    public UserIsAbleToMoveToAppropriateLaunchViaDonutElementSteps(TestContext testContext) {
        super(testContext);
    }

    @When("I choose donut element")
    public void iChooseDonutElement() {
        ElementsCollection gridRowElements = (ElementsCollection) getScenarioContext().getContext(GRID_ROW_ELEMENTS);
        getScenarioContext().setContext(LAUNCH, new GridRow(gridRowElements.first()));
        GridRow launch = (GridRow) getScenarioContext().getContext(LAUNCH);
        getScenarioContext().setContext(LAUNCH_ID, launch.launchId());
    }

    @Then("I am able to move to appropriate launch via {string} element of {string}")
    public void moveToAppropriateLaunchViaDonutElement(String elementIdentificator, String category) {
        GridRow launch = (GridRow) getScenarioContext().getContext(LAUNCH);
        if (!launch.donutElementByType(elementIdentificator).exists()) {
            LOGGER.info("Can't check moving to appropriate via " + category + " because there no data in this category");
        } else {
            launch.donutElementByType(elementIdentificator).click();
            sleep(1000);

            String baseUrl = getBaseURl();
            String project = getProject();
            String launchId = getScenarioContext().getContext(LAUNCH_ID).toString();

            String expectedUrl = baseUrl + "/ui/#" + project + "/launches/all/" + launchId +
                    "?item0Params=filter.eq.hasStats%3Dtrue%26filter.eq.hasChildren%3Dfalse%26filter.in.issueType%3D" +
                    elementIdentificator + "001";

            assertEquals(expectedUrl, getWebDriver().getCurrentUrl());
            back();
        }
    }
}
