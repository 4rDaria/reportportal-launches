package com.epam.reportportal.ui.stepDefinitions;

import com.codeborne.selenide.ElementsCollection;
import com.epam.reportportal.model.user.User;
import com.epam.reportportal.pages.common.ModalWindow;
import com.epam.reportportal.pages.launches.LaunchesPage;
import com.epam.reportportal.services.UserCreator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.epam.reportportal.pages.launches.GridRow;
import com.epam.reportportal.pages.launches.ActionMenu;
import org.openqa.selenium.JavascriptExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.epam.reportportal.services.Login.login;
import static com.epam.reportportal.services.Login.openLoginPage;
import static java.util.Arrays.asList;

public class UserIsAbleToSelectSeveralLaunchesAndCompareThemSteps {

    private List<GridRow> numberLaunchesToCompare;
    private static LaunchesPage launchesPage;

    private ElementsCollection gridRowElements;

    @When("I choose launches {string}")
    public void chooseLaunch(String launchesToCompare) {
        List<String> integersAsString = asList(launchesToCompare.split(","));
        List<Integer> launchesToCompareList = integersAsString.stream()
                .map(s -> Integer.valueOf(s.trim())).collect(Collectors.toList());
        System.out.println(launchesToCompareList.size());

        numberLaunchesToCompare = new ArrayList<>();
        launchesToCompareList.forEach(launch -> numberLaunchesToCompare.add(new GridRow(gridRowElements.get(launch))));

        //select launches
        toggleSelection(numberLaunchesToCompare);
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
        ActionMenu actionMenu = launchesPage.openActionMenu();
        ModalWindow compareModal = actionMenu.compareLaunchesModal();
        compareModal.buttonWithText("Cancel").click();
    }
}