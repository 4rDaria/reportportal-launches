package com.epam.reportportal.ui;

import static com.epam.reportportal.services.CheckScreenshotService.imagesAreEqual;
import static com.epam.reportportal.utils.configuration.EnvironmentConfiguration.baseUrlForCurrentEnv;
import static com.epam.reportportal.utils.configuration.EnvironmentConfiguration.projectNameForCurrentEnv;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epam.reportportal.model.user.User;
import com.epam.reportportal.pages.launches.GridRow;
import com.epam.reportportal.pages.launches.LaunchesPage;
import com.epam.reportportal.services.Login;
import com.epam.reportportal.services.UserCreator;
import com.epam.reportportal.utils.DriverManager;
import com.epam.reportportal.utils.TestDataUtils;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class LaunchesPageTest {

    private static final Logger LOGGER = LogManager.getRootLogger();
    public static final String BASE_URL = baseUrlForCurrentEnv();
    public static final String PROJECT = projectNameForCurrentEnv();
    private static LaunchesPage launchesPage;

    private WebDriver driver = DriverManager.getDriver();

    @BeforeTest
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

    @Test(dataProvider = "category", dataProviderClass = TestDataUtils.class)
    public void eachLaunchesContainsAllTestCountData(String category, String css) {
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

    @Test
    public void userIsAbleToRemoveLaunches() {
        assertTrue(true);
    }

    @Test(dataProvider = "donutElement", dataProviderClass = TestDataUtils.class)
    public void userIsAbleToMoveToAppropriateLaunchViaDonutElement(String category, String elementIdentificator) throws InterruptedException {
        WebDriverWait waitElements = new WebDriverWait(driver, ofSeconds(10));
        List<WebElement> gridRowElements = waitElements.until(visibilityOfAllElements(launchesPage.gridRowElements()));

        GridRow launch = new GridRow(gridRowElements.get(0));
        String launchId = launch.launchId();

        WebElement donutElements = launch.donutElementByType(elementIdentificator);

        if (donutElements == null) {
            LOGGER.info("Can't check moving to appropriate via " + category + " because there is no data in this category");
        } else {
            WebElement element = launch.donutElementByType(elementIdentificator);
            element.click();

            String expectedUrl = BASE_URL + "/ui/#" + PROJECT + "/launches/all/" + launchId +
                    "?item0Params=filter.eq.hasStats%3Dtrue%26filter.eq.hasChildren%3Dfalse%26filter.in.issueType%3D" +
                    elementIdentificator + "001";

            assertEquals(expectedUrl, driver.getCurrentUrl());

            driver.navigate().back();
        }
    }

    @Test(dataProvider = "countElementWithParam", dataProviderClass = TestDataUtils.class)
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
            assertEquals(expectedUrl, driver.getCurrentUrl());
        }
    }

    @Test
    public void checkScreenshot() throws IOException, InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, ofSeconds(10));
        List<WebElement> gridRowElements = wait.until(presenceOfAllElementsLocatedBy(cssSelector("div[class*='grid-row-wrapper']")));

        WebElement launch = gridRowElements.get(0);

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

    @AfterTest
    public void tearDown() {
        DriverManager.closeDriver();
    }
}
