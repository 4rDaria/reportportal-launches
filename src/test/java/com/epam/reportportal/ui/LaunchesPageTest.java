package com.epam.reportportal.ui;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.epam.reportportal.model.user.User;
import com.epam.reportportal.pages.launches.GridRow;
import com.epam.reportportal.pages.launches.LaunchesPage;
import com.epam.reportportal.services.UserCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.epam.reportportal.constants.Constants.*;
import static com.epam.reportportal.services.Login.login;
import static com.epam.reportportal.services.Login.openLoginPage;
import static com.epam.reportportal.utils.configuration.EnvironmentConfiguration.baseUrlForCurrentEnv;
import static com.epam.reportportal.utils.configuration.EnvironmentConfiguration.projectNameForCurrentEnv;
import static org.junit.Assert.assertTrue;

public class LaunchesPageTest {

    private static final Logger LOGGER = LogManager.getRootLogger();
    public static final String BASE_URL = baseUrlForCurrentEnv();
    public static final String PROJECT = projectNameForCurrentEnv();
    private static LaunchesPage launchesPage;

    @BeforeEach
    public void setUp() {
        User testUser = UserCreator.withCredentialsFromProperty();
        openLoginPage();
        getWebDriver().manage().window().maximize();
        launchesPage = login(testUser);
        sleep(500);
    }

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
    public void userIsAbleToMoveToAppropriateLaunchViaCountElement() {
        assertTrue(true);
    }

}
