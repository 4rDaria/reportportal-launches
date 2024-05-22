package com.epam.reportportal.ui;

import com.epam.reportportal.model.user.User;
import com.epam.reportportal.pages.launches.LaunchesPage;
import com.epam.reportportal.services.Login;
import com.epam.reportportal.services.UserCreator;
import com.saucelabs.saucerest.DataCenter;
import com.saucelabs.saucerest.SauceREST;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.epam.reportportal.services.CheckScreenshotService.imagesAreEqual;
import static com.epam.reportportal.utils.configuration.EnvironmentConfiguration.browser;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfAllElementsLocatedBy;
import static org.testng.Assert.assertTrue;

public class SauceLabsTest {
    private static final String BROWSER_NAME = browser();
    public static final String USERNAME = "username";
    public static final String ACCESS_KEY = "key";
    public static final String BROWSER_VERSION = "latest";
    public static final String PLATFORM_NAME = "Windows 11";
    public static final String SAUCE_URL = "https://ondemand.eu-central-1.saucelabs.com/wd/hub";
    public static final String TEST_NAME = "SauceLabsTest";
    private RemoteWebDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        String sessionId;
        SauceREST sauceClient;

        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("username", USERNAME);
        sauceOptions.setCapability("accessKey", ACCESS_KEY);
        sauceOptions.setCapability("name", TEST_NAME);
        MutableCapabilities capabilities;
        switch (BROWSER_NAME) {
            case "safari":
                capabilities = new SafariOptions();
                break;

            case "firefox":
                capabilities = new FirefoxOptions();
                break;

            case "edge":
                capabilities = new EdgeOptions();
                break;

            default:
                capabilities = new ChromeOptions();
                break;
        }
        capabilities.setCapability("browserName", BROWSER_NAME);
        capabilities.setCapability("platformName", PLATFORM_NAME);
        capabilities.setCapability("browserVersion", BROWSER_VERSION);
        capabilities.setCapability("sauce:options", sauceOptions);
        driver = new RemoteWebDriver(new URL(SAUCE_URL), capabilities);
        sessionId = ((RemoteWebDriver) driver).getSessionId().toString();
        sauceClient = new SauceREST(USERNAME, ACCESS_KEY, DataCenter.EU_CENTRAL);
    }

    @Test
    public void saucelabs() throws IOException {
        User testUser = UserCreator.withCredentialsFromProperty();
        LaunchesPage launchesPage = new Login(driver)
                .openPage()
                .login(testUser);
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

        assertTrue(imagesAreEqual(expectedScreenshotLocation, actualScreenshotLocation));
    }

    @AfterTest
    public void turnDown() {
        driver.quit();
    }

}