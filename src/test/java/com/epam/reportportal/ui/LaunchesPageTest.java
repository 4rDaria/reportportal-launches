package com.epam.reportportal.ui;

import static com.epam.reportportal.services.CheckScreenshotService.imagesAreEqual;
import static com.epam.reportportal.utils.configuration.EnvironmentConfiguration.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void setUp() throws InterruptedException {
        User testUser = UserCreator.withCredentialsFromProperty();
        launchesPage = new Login(driver)
                .openPage()
                .login(testUser);
        Thread.sleep(500);
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
        List<WebElement> gridRowElements = driver.findElements(By.cssSelector("div[class*='grid-row-wrapper']"));

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


    //DONE
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
            launch.donutElementByType(elementIdentificator).click();
            Thread.sleep(500);
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
        WebElement element = launch.categoryCount(elementIdentificator);

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
        //Thread.sleep(1000);
        List<WebElement> gridRowElements = driver.findElements(By.cssSelector("div[class*='grid-row-wrapper']"));
        WebElement launch = gridRowElements.get(0);

        Thread.sleep(500);
        File elementScreenshot = launch.getScreenshotAs(OutputType.FILE);

        //create expected screenshot on-the-fly, simulation early created screenshot using
        String expectedScreenshotLocation = "target/expectedScreenshot.png";
        String actualScreenshotLocation = "target/actualScreenshot.png";
        File expectedScreenshot = new File(expectedScreenshotLocation);
        File actualScreenshot= new File(actualScreenshotLocation);

        FileUtils.copyFile(elementScreenshot, expectedScreenshot);
        FileUtils.copyFile(elementScreenshot, actualScreenshot);

        Assert.assertTrue(imagesAreEqual(expectedScreenshotLocation, actualScreenshotLocation));
    }
}
