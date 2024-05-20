package com.epam.reportportal.ui;

import com.epam.reportportal.utils.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    protected WebDriver driver;

    @BeforeEach()
    public void setUp() throws InterruptedException {
        driver = DriverManager.getDriver();
    }

    @AfterEach()
    public void stopBrowser()
    {
        DriverManager.closeDriver();
    }
}
