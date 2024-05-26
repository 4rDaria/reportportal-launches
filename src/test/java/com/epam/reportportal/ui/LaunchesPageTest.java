package com.epam.reportportal.ui;

import static com.epam.reportportal.services.CheckScreenshotService.imagesAreEqual;
import static com.epam.reportportal.utils.DateTimeUtils.descendingSortDateTimeStringFormat;
import static com.epam.reportportal.utils.configuration.EnvironmentConfiguration.*;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

public class LaunchesPageTest extends BaseTest {

    private static final Logger LOGGER = LogManager.getRootLogger();
    public static final String BASE_URL = baseUrlForCurrentEnv();
    public static final String PROJECT = projectNameForCurrentEnv();
    private static LaunchesPage launchesPage;

    private final WebDriver driver = DriverManager.getDriver();

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
    }

    @Test
    public void checkScreenshot() throws IOException, InterruptedException {
        File elementScreenshot = launchesPage.gridRowElements().get(0).getScreenshotAs(OutputType.FILE);

        //create expected screenshot on-the-fly, simulation early created screenshot using
        String expectedScreenshotLocation = "resources/expectedScreenshot.png";
        String actualScreenshotLocation = "target/actualScreenshot.png";
        File expectedScreenshot = new File(expectedScreenshotLocation);
        File actualScreenshot = new File(actualScreenshotLocation);

        FileUtils.copyFile(elementScreenshot, expectedScreenshot);
        FileUtils.copyFile(elementScreenshot, actualScreenshot);

        Assert.assertTrue(imagesAreEqual(expectedScreenshotLocation, actualScreenshotLocation));
    }

    /*
    we don't have resizable elements on launch page.
    following code is only theoretical - step by step what we must do to resize element
    tests for task
        1.	Implement Drag & Drop, Elements Resize using Actions.
        2.	Implement below features using Js executor:
        a.	Scroll to element
        b.	Verification if element is scrolled into view
        c.	Click using JS
     in AdditionalTest class

    @Test
    public void resizeElement() throws InterruptedException {
        List<WebElement> gridRowElements = launchesPage.gridRowElements();
        GridRow launch = new GridRow(gridRowElements.get(0));

        WebElement resizeableElement = launch.donutElementByType(PRODUCT_BUG_DONUT_IDENTIFICATOR);
        resize(resizeableElement, 200, 200);
        Thread.sleep(1000);
    }

    public void resize(WebElement elementToResize, int xOffset, int yOffset) {
        try {
            if (elementToResize.isDisplayed()) {
                Actions action = new Actions(driver);
                action.clickAndHold(elementToResize).moveByOffset(xOffset, yOffset).release().build().perform();
            } else {
                System.out.println("Element was not displayed to drag");
            }
        } catch (StaleElementReferenceException e) {
            System.out.println("Element with " + elementToResize + "is not attached to the page document "  + e.getStackTrace());
        } catch (NoSuchElementException e) {
            System.out.println("Element " + elementToResize + " was not found in DOM " + e.getStackTrace());
        } catch (Exception e) {
            System.out.println("Unable to resize" + elementToResize + " - " + e.getStackTrace());
        }
    }
    */
}
