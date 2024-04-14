package com.epam.reportportal.ui.stepDefinitions;

import com.codeborne.selenide.ElementsCollection;
import com.epam.reportportal.cucumber.TestContext;
import com.epam.reportportal.pages.common.ModalWindow;
import com.epam.reportportal.pages.launches.LaunchesPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.epam.reportportal.pages.launches.GridRow;
import com.epam.reportportal.pages.launches.ActionMenu;
import org.openqa.selenium.JavascriptExecutor;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.epam.reportportal.cucumber.Context.GRID_ROW_ELEMENTS;
import static com.epam.reportportal.cucumber.Context.LAUNCH_PAGE;
import static java.util.Arrays.asList;

public class UserIsAbleToSelectSeveralLaunchesAndCompareThemSteps extends BaseStep{

    private List<GridRow> numberLaunchesToCompare;

    public UserIsAbleToSelectSeveralLaunchesAndCompareThemSteps(TestContext testContext) {
        super(testContext);
    }

    @When("I choose launches {string}")
    public void i_choose_launches(String launchesToCompare) {
        ElementsCollection gridRowElements = (ElementsCollection) getScenarioContext().getContext(GRID_ROW_ELEMENTS);
        List<Integer> launchesToCompareList = launchesToCompareList(launchesToCompare);
        numberLaunchesToCompare = new ArrayList<>();
        launchesToCompareList.forEach(launch -> numberLaunchesToCompare.add(new GridRow(gridRowElements.get(launch))));

        //select launches
        toggleSelection(numberLaunchesToCompare);
    }

    private List<Integer> launchesToCompareList(String launchesToCompare) {
        return asList(launchesToCompare.split(","))
                .stream()
                .map(String::trim)
                .map(Integer::valueOf)
                .toList();
    }

    @Then("I can compare launches")
    public void compareLaunches() {
        openAndCloseActionMenu();

        //unselect launches
        toggleSelection(numberLaunchesToCompare);
    }

    private void toggleSelection(List<GridRow> numberLaunchesToCompare) {
        JavascriptExecutor jse = (JavascriptExecutor) getWebDriver();
        for (GridRow launch : numberLaunchesToCompare) {
            jse.executeScript("arguments[0].click();", launch.checkbox());
        }
    }

    private void openAndCloseActionMenu() {
        LaunchesPage launchesPage = (LaunchesPage) getScenarioContext().getContext(LAUNCH_PAGE);
        ActionMenu actionMenu = launchesPage.openActionMenu();
        ModalWindow compareModal = actionMenu.compareLaunchesModal();
        compareModal.buttonWithText("Cancel").click();
    }
}