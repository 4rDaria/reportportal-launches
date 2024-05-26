package com.epam.reportportal.utils;

import static com.epam.reportportal.utils.configuration.EnvironmentConfiguration.browser;

import com.saucelabs.saucerest.DataCenter;
import com.saucelabs.saucerest.SauceREST;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.net.MalformedURLException;
import java.net.URL;
import lombok.Data;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

@Data
public class DriverManager {

    private static final String BROWSER_NAME = browser();
    public static final String USERNAME = "test";
    public static final String ACCESS_KEY = "test";
    public static final String BROWSER_VERSION = "latest";
    public static final String PLATFORM_NAME = "Windows 11";
    public static final String SAUCE_URL = "https://ondemand.eu-central-1.saucelabs.com:443/wd/hub";
    public static final String TEST_NAME = "SauceLabsTest";

    private static WebDriver driver;

    public static WebDriver getDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }

    public static RemoteWebDriver sauceLabsTestSetUp() throws MalformedURLException {
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
        RemoteWebDriver remoteWebDriver = new RemoteWebDriver(new URL(SAUCE_URL), capabilities);
        sessionId = ((RemoteWebDriver) remoteWebDriver).getSessionId().toString();
        sauceClient = new SauceREST(USERNAME, ACCESS_KEY, DataCenter.EU_CENTRAL);
        return remoteWebDriver;
    }
}
