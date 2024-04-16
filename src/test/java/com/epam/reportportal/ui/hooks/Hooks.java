package com.epam.reportportal.ui.hooks;

import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.After;


public class Hooks {

    @After("@ui")
    public void tearDownForUi() {
        WebDriverRunner.closeWebDriver();
    }
}
