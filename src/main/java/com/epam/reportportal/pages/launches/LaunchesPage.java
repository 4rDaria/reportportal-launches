package com.epam.reportportal.pages.launches;

import com.epam.reportportal.services.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import static com.epam.reportportal.utils.configuration.EnvironmentConfiguration.baseUrlForCurrentEnv;
import static com.epam.reportportal.utils.configuration.EnvironmentConfiguration.projectNameForCurrentEnv;

public class LaunchesPage extends AbstractPage {

    private static final String BASE_URL = baseUrlForCurrentEnv();
    private static final String PROJECT = projectNameForCurrentEnv();
    protected final int WAIT_TIMEOUT_SECONDS = 5;


    public LaunchesPage(WebDriver driver)
    {
        super(driver);
    }

    @Override
    public LaunchesPage openPage()
    {
        driver.navigate().to(BASE_URL + "/ui/#" + PROJECT + "/launches/all");
        return this;
    }

    public List<WebElement> gridRowElements() {
        Wait<WebDriver> wait = new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(50))
            .pollingEvery(Duration.ofMillis(500))
            .ignoring(NoSuchElementException.class);

        return wait.until(new Function<WebDriver, List<WebElement>>() {
            public List<WebElement> apply(WebDriver driver) {
                List<WebElement> elements = driver.findElements(By.cssSelector("div[class*='grid-row-wrapper']"));
                if (elements.size() > 0) {
                    return elements;
                } else {
                    throw new NoSuchElementException("No elements found with the specified locator.");
                }
            }
        });
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