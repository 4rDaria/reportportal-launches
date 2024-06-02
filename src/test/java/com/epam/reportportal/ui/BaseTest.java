package com.epam.reportportal.ui;

import com.epam.reportportal.utils.DriverManager;
import org.junit.jupiter.api.AfterEach;

public class BaseTest {

    @AfterEach()
    public void stopBrowser()
    {
        DriverManager.closeDriver();
    }
}