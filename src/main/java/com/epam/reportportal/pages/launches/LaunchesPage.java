package com.epam.reportportal.pages.launches;

import com.epam.reportportal.services.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static com.epam.reportportal.utils.configuration.EnvironmentConfiguration.baseUrlForCurrentEnv;
import static com.epam.reportportal.utils.configuration.EnvironmentConfiguration.projectNameForCurrentEnv;

public class LaunchesPage extends AbstractPage {

    public static final String BASE_URL = baseUrlForCurrentEnv();
    public static final String PROJECT = projectNameForCurrentEnv();
    protected final int WAIT_TIMEOUT_SECONDS = 5;


    public LaunchesPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public LaunchesPage openPage()
    {
        driver.navigate().to(BASE_URL + "/ui/#" + PROJECT + "/launches/all");
        return this;
    }

    public List<WebElement> gridRowElements() {
        WebElement linkLoggedInUser = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class*='grid-row-wrapper']")));
        return driver.findElements(By.cssSelector("div[class*='grid-row-wrapper']"));
    }

    public List<WebElement> titles() {
        return driver.findElements(By.cssSelector("[class*='title-full']"));
    }

    public ActionMenu openActionMenu() {
        WebElement menuButton = driver.findElement(By.cssSelector("div[class*='ghost-menu-button']"));
        menuButton.click();
        WebElement menuElement = driver.findElement(By.cssSelector("div[class*='ghostMenuButton__menu--']"));
        return new ActionMenu(menuElement);
    }
}
