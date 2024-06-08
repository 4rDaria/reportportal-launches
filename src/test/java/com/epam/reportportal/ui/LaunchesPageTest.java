package com.epam.reportportal.ui;

import static com.epam.reportportal.utils.DateTimeUtils.descendingSortDateTimeStringFormat;
import static com.epam.reportportal.utils.configuration.EnvironmentConfiguration.*;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.epam.reportportal.junit5.ReportPortalExtension;
import com.epam.reportportal.model.user.User;
import com.epam.reportportal.pages.common.ModalWindow;
import com.epam.reportportal.pages.launches.ActionMenu;
import com.epam.reportportal.pages.launches.GridRow;
import com.epam.reportportal.pages.launches.HamburgerMenu;
import com.epam.reportportal.pages.launches.LaunchesPage;
import com.epam.reportportal.services.Login;
import com.epam.reportportal.services.UserCreator;
import com.epam.reportportal.ui.helper.Titles;
import com.epam.reportportal.utils.DateTimeUtils;
import com.epam.reportportal.utils.DriverManager;
import java.util.Collections;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.*;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(ReportPortalExtension.class)
public class LaunchesPageTest extends BaseTest {

    private static final Logger LOGGER = LogManager.getRootLogger();
    private static final String BASE_URL = baseUrlForCurrentEnv();
    private static final String PROJECT = projectNameForCurrentEnv();
    private static LaunchesPage launchesPage;

    private final WebDriver driver = DriverManager.getInstance().getDriver();

    @BeforeEach
    public void setUp() {
        User testUser = UserCreator.withCredentialsFromProperty();
        launchesPage = new Login(driver)
            .openPage()
            .login(testUser);
    }

    @Test
    @Order(1)
    public void launchesListSortedByMostRecentByDefault() {
        List<String> actualStartedTimes = getLaunchesStartTime(launchesPage.gridRowElements());

        List<String> expectedStartedTimes = descendingSortDateTimeStringFormat(getLaunchesStartTime(launchesPage.gridRowElements()));

        LOGGER.info("launches list sorted by most recent by default");
        assertEquals(expectedStartedTimes, actualStartedTimes);
    }

    private List<String> getLaunchesStartTime(List<WebElement> gridRowElements) {
        List<String> startTimes = new ArrayList<>();

        for (WebElement gridRow : gridRowElements) {
            GridRow launch = new GridRow(gridRow);
            startTimes.add(launch.getStartTime());
        }
        return startTimes;
    }

    @ParameterizedTest(name = "{index} => user can resort launches via =''{0}''")
    @MethodSource("com.epam.reportportal.utils.TestDataUtils#categoryWithTitle")
    public void userCanResortLaunchesByEachCategoriesCount(String category, Titles title) {
        List<Integer> counts = countsByCategory(launchesPage.gridRowElements(), category);

        launchesPage.titles().get(title.getValue()).click();

        Collections.sort(counts);

        List<Integer> orderedCounts = countsByCategory(launchesPage.gridRowElements(), category);

        LOGGER.info("user can resort launches by each categories count");
        Assert.assertTrue(counts.equals(orderedCounts));
    }

    private List<Integer> countsByCategory(List<WebElement> gridRowElements, String category) {
        List<Integer> counts = new ArrayList<>();
        for (WebElement gridRow : gridRowElements) {
            GridRow launch = new GridRow(gridRow);
            WebElement element = launch.categoryCount(category);

            if (launch.categoryCount(category) == null) {
                counts.add(0);
            } else {
                counts.add(Integer.parseInt(element.getText()));
            }
        }
        return counts;
    }

    @ParameterizedTest(name = "{index} => launch contains tests count data for ''{0}''")
    @MethodSource("com.epam.reportportal.utils.TestDataUtils#category")
    public void eachLaunchesContainsAllTestCountData(String category, String css) {
        boolean eachLaunchesContainsAllTestCountData = true;

        for (WebElement gridRow : launchesPage.gridRowElements()) {
            GridRow launch = new GridRow(gridRow);

            String id = launch.launchId();
            if ( launch.category(css) == null) {
                eachLaunchesContainsAllTestCountData = false;
                LOGGER.warn("Element " + category + " not found in launch: " + id);
            }
        }

        LOGGER.info("each launches contains all test count data");
        assertTrue(eachLaunchesContainsAllTestCountData);

    }

    @ParameterizedTest(name = "{index} => user is able select ''{0}'' launches and compare")
    @MethodSource("com.epam.reportportal.utils.TestDataUtils#launchesToCompare")
    public void userIsAbleToSelectSeveralLaunchesAndCompareThem(String testCase, List<Integer> launchesToCompare)
        throws InterruptedException {

        List<GridRow> numberLaunchesToCompare = new ArrayList<>();
        launchesToCompare.forEach(launch -> numberLaunchesToCompare.add(new GridRow(launchesPage.gridRowElements().get(launch))));

        //select launches
        toggleSelection(numberLaunchesToCompare);

        openAndCloseActionMenu();

        //unselect launches
        toggleSelection(numberLaunchesToCompare);

        LOGGER.info("user is able to select several launches and compare them");
    }

    private void toggleSelection(List<GridRow> numberLaunchesToCompare) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        for (GridRow launch : numberLaunchesToCompare) {
            jse.executeScript("arguments[0].click();", launch.checkbox());
        }
    }

    private void openAndCloseActionMenu() throws InterruptedException {
        ActionMenu actionMenu = launchesPage.openActionMenu();
        ModalWindow compareModal = actionMenu.compareLaunchesModal();
        compareModal.buttonWithText("Cancel").click();
    }

    @Test
    public void userIsAbleToRemoveLaunches() throws InterruptedException {
        if (launchesPage.gridRowElements().isEmpty()) {
            LOGGER.info("Test userIsAbleToRemoveLaunches was skipped because launches count is 0 and nothing to delete");
        } else {
            GridRow launchToRemove = new GridRow(launchesPage.gridRowElements().get(0));
            int expectedLaunchsCountAfterDelete = launchesPage.gridRowElements().size() - 1;
            String deletedLaunchId = launchToRemove.launchId();

            launchToRemove.hamburgerMenu().deleteLaunch(driver);

            LOGGER.info("Launch with id" + deletedLaunchId + " was successfully deleted");
            int actualLaunchesCount = launchesPage.gridRowElements().size();
            Assert.assertEquals(expectedLaunchsCountAfterDelete, actualLaunchesCount);
        }
    }

    @ParameterizedTest(name = "{index} => user is able to move to appropriate launch clicking ''{0}''")
    @MethodSource("com.epam.reportportal.utils.TestDataUtils#donutElement")
    public void userIsAbleToMoveToAppropriateLaunchViaDonutElement(String category, String elementIdentificator) {
        GridRow launch = new GridRow(launchesPage.gridRowElements().get(0));
        String launchId = launch.launchId();

        if (launch.donutElementByType(elementIdentificator) == null) {
            LOGGER.info("Can't check moving to appropriate via " + category + " because there is no data in this category");
        } else {
            launch.donutElementByType(elementIdentificator).click();

            String expectedUrl = BASE_URL + "/ui/#" + PROJECT + "/launches/all/" + launchId +
                "?item0Params=filter.eq.hasStats%3Dtrue%26filter.eq.hasChildren%3Dfalse%26filter.in.issueType%3D" +
                elementIdentificator + "001";

            assertEquals(expectedUrl, driver.getCurrentUrl());

            driver.navigate().back();
        }

        LOGGER.info("user is able to move to appropriate launch via donut element");
    }

    @ParameterizedTest(name = "{index} => user is able to move to appropriate launch clicking ''{0}''")
    @MethodSource("com.epam.reportportal.utils.TestDataUtils#countElementWithParam")
    public void userIsAbleToMoveToAppropriateLaunchClickingCountElement(String category,
                                                                        String elementIdentificator,
                                                                        String param) {
        GridRow launch = new GridRow(launchesPage.gridRowElements().get(0));
        String launchId = launch.launchId();

        if (launch.categoryCount(elementIdentificator) == null) {
            LOGGER.info("Can't check moving to appropriate via " + elementIdentificator +
                " because there no data in this category");
        } else {
            launch.categoryCount(elementIdentificator).click();

            String expectedUrl = BASE_URL + "/ui/#" + PROJECT + "/launches/all/" + launchId +
                "?item0Params=filter.eq.hasStats%3Dtrue%26filter.eq.hasChildren%3Dfalse%26filter."
                + "in.type%3DSTEP%26filter.in.status%3D" + param;
            assertEquals(expectedUrl, driver.getCurrentUrl());
        }

        LOGGER.info("user is able to move to appropriate launch clicking count element");
    }

}