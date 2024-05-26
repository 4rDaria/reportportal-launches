package com.epam.reportportal.ui;

import static com.epam.reportportal.constants.Constants.PASSED_COUNT_CELL_CSS;
import static com.epam.reportportal.constants.Constants.PRODUCT_BUG_DONUT_IDENTIFICATOR;
import static com.epam.reportportal.services.CheckScreenshotService.imagesAreEqual;
import static com.epam.reportportal.utils.configuration.EnvironmentConfiguration.*;
import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfAllElementsLocatedBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElements;

import com.epam.reportportal.model.user.User;
import com.epam.reportportal.pages.common.ModalWindow;
import com.epam.reportportal.pages.launches.ActionMenu;
import com.epam.reportportal.pages.launches.GridRow;
import com.epam.reportportal.pages.launches.LaunchesPage;
import com.epam.reportportal.services.Login;
import com.epam.reportportal.services.UserCreator;
import com.epam.reportportal.utils.DriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
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
    public void launchesListSortedByMostRecentByDefault() {
        assertTrue(true);
    }

    @Test
    public void userCanResortLaunchesByEachCategoriesCount() {
        assertTrue(true);
    }

    @ParameterizedTest(name = "{index} => launch contains tests count data for ''{0}''")
    @MethodSource("com.epam.reportportal.utils.TestDataUtils#category")
    public void eachLaunchesContainsAllTestCountData(String category, String css) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, ofSeconds(10));
        List<WebElement> gridRowElements = wait.until(visibilityOfAllElements(launchesPage.gridRowElements()));

        for (WebElement gridRow : gridRowElements) {
            GridRow launch = new GridRow(gridRow);
            WebElement element = launch.category(css);
            String id = launch.launchId();
            if (element == null) {
                LOGGER.warn("Element " + category + " not found in launch: " + id);
            }
        }
    }

    @ParameterizedTest(name = "{index} => user is able select ''{0}'' launches and compare")
    @MethodSource("com.epam.reportportal.utils.TestDataUtils#launchesToCompare")
    public void userIsAbleToSelectSeveralLaunchesAndCompareThem(String testCase, List<Integer> launchesToCompare) throws InterruptedException {
        List<WebElement> gridRowElements = launchesPage.gridRowElements();

        List<GridRow> numberLaunchesToCompare = new ArrayList<>();
        launchesToCompare.forEach(launch -> numberLaunchesToCompare.add(new GridRow(gridRowElements.get(launch))));

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
    public void userIsAbleToRemoveLaunches() {
        assertTrue(true);
    }


    @ParameterizedTest(name = "{index} => user is able to move to appropriate launch clicking ''{0}''")
    @MethodSource("com.epam.reportportal.utils.TestDataUtils#donutElement")
    public void userIsAbleToMoveToAppropriateLaunchViaDonutElement(String category, String elementIdentificator) throws InterruptedException {
        List<WebElement> gridRowElements = launchesPage.gridRowElements();
        GridRow launch = new GridRow(gridRowElements.get(0));
        String launchId = launch.launchId();

        WebElement donutElements = launch.donutElementByType(elementIdentificator);

        if (donutElements == null) {
            LOGGER.info("Can't check moving to appropriate via " + category + " because there is no data in this category");
        } else {
            WebDriverWait wait = new WebDriverWait(driver, ofSeconds(10));
            WebElement element = wait.until(ExpectedConditions.visibilityOf(launch.donutElementByType(elementIdentificator)));
            element.click();

            String expectedUrl = BASE_URL + "/ui/#" + PROJECT + "/launches/all/" + launchId +
                    "?item0Params=filter.eq.hasStats%3Dtrue%26filter.eq.hasChildren%3Dfalse%26filter.in.issueType%3D" +
                    elementIdentificator + "001";

            Assert.assertEquals(expectedUrl, driver.getCurrentUrl());

            driver.navigate().back();
        }
    }

    @ParameterizedTest(name = "{index} => user is able to move to appropriate launch clicking ''{0}''")
    @MethodSource("com.epam.reportportal.utils.TestDataUtils#countElementWithParam")
    public void userIsAbleToMoveToAppropriateLaunchClickingCountElement(String category,
                                                                        String elementIdentificator,
                                                                        String param) {
        List<WebElement> gridRowElements = launchesPage.gridRowElements();
        GridRow launch = new GridRow(gridRowElements.get(0));
        String launchId = launch.launchId();

        WebDriverWait wait = new WebDriverWait(driver, ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOf(launch.categoryCount(elementIdentificator)));

        if (element == null) {
            LOGGER.info("Can't check moving to appropriate via " + elementIdentificator +
                    " because there no data in this category");
        } else {
            element.click();

            String expectedUrl = BASE_URL + "/ui/#" + PROJECT + "/launches/all/" + launchId +
                    "?item0Params=filter.eq.hasStats%3Dtrue%26filter.eq.hasChildren%3Dfalse%26filter."
                    + "in.type%3DSTEP%26filter.in.status%3D" + param;
            Assert.assertEquals(expectedUrl, driver.getCurrentUrl());
        }
    }

    @Test
    public void checkScreenshot() throws IOException, InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, ofSeconds(10));
        List<WebElement> gridRowElements = wait.until(presenceOfAllElementsLocatedBy(cssSelector("div[class*='grid-row-wrapper']")));

        WebElement launch = gridRowElements.get(0);

        File elementScreenshot = launch.getScreenshotAs(OutputType.FILE);

        //create expected screenshot on-the-fly, simulation early created screenshot using
        String expectedScreenshotLocation = "resources/expectedScreenshot.png";
        String actualScreenshotLocation = "target/actualScreenshot.png";
        File expectedScreenshot = new File(expectedScreenshotLocation);
        File actualScreenshot= new File(actualScreenshotLocation);

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
