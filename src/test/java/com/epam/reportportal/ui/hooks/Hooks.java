package com.epam.reportportal.ui.hooks;

import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.After;
import io.cucumber.java.Before;


public class Hooks {

    //private UIContext uiContext = UIContext.getInstance();

    @Before()
    public void setupForUI() {
        System.out.println("BEFORE");
    }

    @After()
    public void tearDownForUi() {
        //uiContext.getReport().write(scenario);
        WebDriverRunner.closeWebDriver();
    }

//    @After("@ui")
//    public void tearDownForUi(Scenario scenario) throws IOException {
//        uiContext.getReport().write(scenario);
//        uiContext.getReport().captureScreenShot(scenario, uiContext.getWebDriver());
//        uiContext.getWebDriver().quit();
//    }
}
