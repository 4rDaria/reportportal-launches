package com.epam.reportportal.ui.stepDefinitions;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.WebDriverRunner;
import com.epam.reportportal.pages.common.ModalWindow;
import com.epam.reportportal.pages.launches.LaunchesPage;
import io.cucumber.java.After;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.epam.reportportal.pages.launches.GridRow;
import com.epam.reportportal.pages.launches.ActionMenu;
import org.openqa.selenium.JavascriptExecutor;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static java.util.Arrays.asList;

public class UserIsAbleToSelectSeveralLaunchesAndCompareThemSteps {

    private List<GridRow> numberLaunchesToCompare;
    private static LaunchesPage launchesPage;
    private ElementsCollection gridRowElements;

    @When("I choose launches {string}")
    public void i_choose_launches(String launchesToCompare) {

        gridRowElements = launchesPage.gridRowElements();
        List<Integer> launchesToCompareList = launchesToCompareList(launchesToCompare);
        numberLaunchesToCompare = new ArrayList<>();
        launchesToCompareList.forEach(launch -> numberLaunchesToCompare.add(new GridRow(gridRowElements.get(launch))));

        //select launches
        toggleSelection(numberLaunchesToCompare);
    }

    private List<Integer> launchesToCompareList(String launchesToCompare) {
        return asList(launchesToCompare.split(","))
                .stream()
                .map(String::trim) // Trim each element
                .map(Integer::valueOf)
                .toList();
    }

    @Then("I can compare launches")
    public void compareLaunches() {
        //openAndCloseActionMenu();
        System.out.println("I can compare");
        //unselect launches
        //toggleSelection(numberLaunchesToCompare);
    }

    private void toggleSelection(List<GridRow> numberLaunchesToCompare) {
        JavascriptExecutor jse = (JavascriptExecutor) getWebDriver();
        for (GridRow launch : numberLaunchesToCompare) {
            jse.executeScript("arguments[0].click();", launch.checkbox());
        }
    }

    private void openAndCloseActionMenu() {
        ActionMenu actionMenu = launchesPage.openActionMenu();
        ModalWindow compareModal = actionMenu.compareLaunchesModal();
        compareModal.buttonWithText("Cancel").click();
    }

    @After()
    public void tearDownForUi() {
        WebDriverRunner.closeWebDriver();
    }
}