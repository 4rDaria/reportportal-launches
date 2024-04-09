package com.epam.reportportal.services;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static com.epam.reportportal.utils.configuration.EnvironmentConfiguration.baseUrlForCurrentEnv;
import static com.epam.reportportal.utils.configuration.EnvironmentConfiguration.projectNameForCurrentEnv;

import com.epam.reportportal.model.user.User;
import com.epam.reportportal.pages.launches.LaunchesPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class Login {
    private static final Logger LOGGER = LogManager.getRootLogger();
    public static final String BASE_URL = baseUrlForCurrentEnv();
    public static final String PROJECT = projectNameForCurrentEnv();
    private static final String LOGIN_PAGE_URL = BASE_URL + "/ui/#login";
    private static void setInputLogin(String username) {
        $(By.name("login")).setValue(username);
    }

    private static void setInputPassword(String password) {
        $(By.name("password")).setValue(password);
    }

    private static void clickLoginButton() {
        $x("//button[contains(text(), 'Login')]").click();
    }

    public static Login openLoginPage()
    {
        open(LOGIN_PAGE_URL);
        try {
            open(LOGIN_PAGE_URL);
            LOGGER.info("Login page opened successfully");
        } catch (Exception e) {
            LOGGER.error("Failed to open login page: " + e.getMessage());
        }
        return new Login();
    }

    public static LaunchesPage login(User user)
    {
        setInputLogin(user.getUsername());
        setInputPassword(user.getPassword());
        clickLoginButton();
        LOGGER.info("Login performed");
        open(BASE_URL + "/ui/#" + PROJECT + "/launches/all");
        return new LaunchesPage();
    }
}

