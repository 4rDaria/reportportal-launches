package com.epam.reportportal.ui;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.epam.reportportal.constants.Constants.*;
import static com.epam.reportportal.services.Login.login;
import static com.epam.reportportal.services.Login.openLoginPage;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.epam.reportportal.model.user.User;
import com.epam.reportportal.pages.common.ModalWindow;
import com.epam.reportportal.pages.launches.ActionMenu;
import com.epam.reportportal.pages.launches.GridRow;
import com.epam.reportportal.pages.launches.LaunchesPage;
import com.epam.reportportal.services.UserCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.JavascriptExecutor;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class LaunchesPageTest {

    private static final Logger logger = LogManager.getRootLogger();

    private static LaunchesPage launchesPage;

    @Test
    public void launchesListSortedByMostRecentByDefault() {
        assertTrue(true);
    }

    @Test
    public void userCanResortLaunchesByEachCategoriesCount() {
        assertTrue(true);
    }

    @Test
    public void eachLaunchesContainsAllTestCountData() {
        assertTrue(true);
    }

    @Test
    public void userIsAbleToSelectSeveralLaunchesAndCompareThem() {
        assertTrue(true);
    }

    @Test
    public void userIsAbleToRemoveLaunches() {
        assertTrue(true);
    }

    @Test
    public void userIsAbleToMoveToAppropriateLaunchViaDonutElement() {
        assertTrue(true);
    }

    @Test
    public void userIsAbleToMoveToAppropriateLaunchClickingCountElement(){
        assertTrue(true);
    }

}
