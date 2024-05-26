package com.epam.reportportal.ui;

import com.epam.reportportal.model.user.User;
import com.epam.reportportal.pages.launches.LaunchesPage;
import com.epam.reportportal.services.Login;
import com.epam.reportportal.services.UserCreator;
import com.epam.reportportal.utils.DriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.epam.reportportal.services.CheckScreenshotService.imagesAreEqual;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfAllElementsLocatedBy;

class SauceLabsTest {

    @Test
    public void saucelabs() throws IOException {
        User testUser = UserCreator.withCredentialsFromProperty();
        RemoteWebDriver driver = DriverManager.sauceLabsTestSetUp();
        LaunchesPage launchesPage = new Login(driver)
                .openPage()
                .login(testUser);
        WebDriverWait wait = new WebDriverWait(driver, ofSeconds(10));
        List<WebElement> gridRowElements = wait.until(presenceOfAllElementsLocatedBy(cssSelector("div[class*='grid-row-wrapper']")));

        WebElement launch = gridRowElements.get(0);

        File elementScreenshot = launch.getScreenshotAs(OutputType.FILE);

        //create expected screenshot on-the-fly, simulation early created screenshot using
        String expectedScreenshotLocation = "resources/expectedScreenshot.png";
        String actualScreenshotLocation = "target/actualScreenshot.png";
        File expectedScreenshot = new File(expectedScreenshotLocation);
        File actualScreenshot= new File(actualScreenshotLocation);

        FileUtils.copyFile(elementScreenshot, expectedScreenshot);
        FileUtils.copyFile(elementScreenshot, actualScreenshot);

        Assert.assertTrue(imagesAreEqual(expectedScreenshotLocation, actualScreenshotLocation));
        driver.quit();
    }

}
