package com.epam.reportportal.pages.launches;

import com.epam.reportportal.pages.common.ElWrapper;
import com.epam.reportportal.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.epam.reportportal.constants.Constants.START_TIME_CELL_CSS;

public class GridRow extends ElWrapper {

    public GridRow(WebElement element) {
        super(element);
    }

    public String launchId() {
        return element.getAttribute("data-id");
    }

    public WebElement category(String category) {
        return element.findElement(By.cssSelector("div[class*='" + category + "']"));
    }

    public WebElement categoryCount(String category) {
        try {
            WebElement categoryElement = category(category);
            WebElement divElement = categoryElement.findElement(By.tagName("div"));
            return divElement.findElement(By.tagName("a"));
        }  catch (NoSuchElementException e) {
            return null;
        }
    }

    public String startTime() {
        WebElement categoryElement = category(START_TIME_CELL_CSS);
        WebElement divElement = categoryElement.findElement(By.tagName("div"));
        List<WebElement> spanElements = divElement.findElements(By.tagName("span"));
        WebElement lastSpanElement = spanElements.get(spanElements.size() - 1);
        return lastSpanElement.getText();
    }

    public WebElement checkbox() {
        return element.findElement(By.cssSelector("input[type='checkbox']"));
    }

    public HamburgerMenu hamburgerMenu() {
        WebDriver driver = DriverManager.getDriver();
        element.findElement(By.cssSelector("div[class*='hamburger-icon--']")).click();
        WebElement hamburgerMenuActions = driver.findElement(By.cssSelector("div[class*='hamburger-menu-actions']"));
        return new HamburgerMenu(hamburgerMenuActions);
    }

    public WebElement donutElementByType(String type) {
        try {
            String xpath = String.format(".//div[contains(@class,'launchSuiteGrid__%s')]//*[@class='donut']", type);
            WebElement donutElement = element.findElement(By.xpath(xpath));
            return donutElement;
        } catch (NoSuchElementException e) {
            return null;
        }
    }

}