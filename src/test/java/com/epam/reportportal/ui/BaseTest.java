package com.epam.reportportal.ui;

import com.epam.reportportal.utils.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    protected WebDriver driver;

    @BeforeTest()
    public void setUp() throws InterruptedException {
        driver = DriverManager.getDriver();
    }

    @AfterTest()
    public void stopBrowser()
    {
        DriverManager.closeDriver();
    }
}