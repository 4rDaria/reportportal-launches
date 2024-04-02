package com.epam.reportportal.services;

import com.epam.reportportal.model.user.User;
import com.epam.reportportal.pages.launches.LaunchesPage;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;

@Component
@NoArgsConstructor
public class Login {
    private static final Logger logger = LogManager.getRootLogger();
    private static final String LOGIN_PAGE_URL = baseUrl + "/ui/#login";

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
            logger.info("Login page opened successfully");
        } catch (Exception e) {
            logger.error("Failed to open login page: " + e.getMessage());
        }
        return new Login();
    }

    public static LaunchesPage login(User user)
    {
        setInputLogin(user.getUsername());
        setInputPassword(user.getPassword());
        clickLoginButton();
        logger.info("Login performed");
        return new LaunchesPage();
    }
}

