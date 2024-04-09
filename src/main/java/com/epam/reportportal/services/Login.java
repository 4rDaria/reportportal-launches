package com.epam.reportportal.services;

import com.epam.reportportal.model.user.User;
import com.epam.reportportal.pages.launches.LaunchesPage;
import com.epam.reportportal.utils.configuration.EnvironmentConfiguration;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;
import static com.epam.reportportal.utils.configuration.EnvironmentConfiguration.projectNameForCurrentEnv;

@Component
@NoArgsConstructor
public class Login {
    private static final Logger LOGGER = LogManager.getRootLogger();
    public static final String BASE_URL = EnvironmentConfiguration.baseUrlForCurrentEnv();
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

