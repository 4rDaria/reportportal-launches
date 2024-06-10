package com.epam.reportportal.utils;

import static com.epam.reportportal.utils.configuration.EnvironmentConfiguration.browser;

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
    private static final String USERNAME = "test";
    private static final String ACCESS_KEY = "test";
    private static final String BROWSER_VERSION = "latest";
    private static final String PLATFORM_NAME = "Windows 11";
    private static final String SAUCE_URL = "https://ondemand.eu-central-1.saucelabs.com:443/wd/hub";
    private static final String TEST_NAME = "SauceLabsTest";

    private static DriverManager instance;
    private static WebDriver driver;

    private DriverManager() {}

    public static WebDriver getDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        return driver;
    }

    public static synchronized DriverManager getInstance() {
        if (instance == null) {
            instance = new DriverManager();
        }
        return instance;
    }

    public static void closeDriver() {
        driver.quit();
    }
}