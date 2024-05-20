package com.epam.reportportal.services;

import com.epam.reportportal.model.user.User;
import com.epam.reportportal.pages.launches.LaunchesPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.epam.reportportal.utils.configuration.EnvironmentConfiguration.baseUrlForCurrentEnv;
import static com.epam.reportportal.utils.configuration.EnvironmentConfiguration.projectNameForCurrentEnv;

public class Login extends AbstractPage{
    private static final Logger LOGGER = LogManager.getRootLogger();
    public static final String BASE_URL = baseUrlForCurrentEnv();
    public static final String PROJECT = projectNameForCurrentEnv();
    private static final String LOGIN_PAGE_URL = BASE_URL + "/ui/#login";

    @FindBy(name = "login")
    private WebElement loginInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[contains(text(), 'Login')]")
    private WebElement loginButton;

    public Login(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public Login openPage() {
        driver.navigate().to(LOGIN_PAGE_URL);
        LOGGER.info("Login page opened successfully");
        return this;
    }

    public LaunchesPage login(User user) {
        loginInput.sendKeys(user.getUsername());
        passwordInput.sendKeys(user.getPassword());
        loginButton.click();
        LOGGER.info("Login performed");
        driver.navigate().to(BASE_URL + "/ui/#" + PROJECT + "/launches/all");
        return new LaunchesPage(driver);
    }
}

