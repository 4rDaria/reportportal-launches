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
        return getAttributeByName("data-id");
    }

    public WebElement category(String category) {
        return findElementByLocator(By.cssSelector("div[class*='" + category + "']"));
    }

    public WebElement categoryCount(String category) {
        WebElement categoryCountElement;
        WebElement categoryElement = category(category);
        WebElement divElement = categoryElement.findElement(By.tagName("div"));
        if (divElement.isEnabled()) {
            categoryCountElement = divElement.findElement(By.tagName("a"));
        } else {
            categoryCountElement = null;
        }
        return categoryCountElement;
    }

    public String startTime() {
        WebElement categoryElement = category(START_TIME_CELL_CSS);
        WebElement divElement = categoryElement.findElement(By.tagName("div"));
        List<WebElement> spanElements = divElement.findElements(By.tagName("span"));
        WebElement lastSpanElement = spanElements.get(spanElements.size() - 1);
        return lastSpanElement.getText();
    }

    public WebElement checkbox() {
        return findElementByLocator(By.cssSelector("input[type='checkbox']"));
    }

    public HamburgerMenu hamburgerMenu() {
        clickHamburgerMenu();
        WebElement hamburgerMenuActions = findAnotherElement(By.cssSelector("div[class*='hamburger-menu-actions']"));
        return new HamburgerMenu(hamburgerMenuActions);
    }

    private void clickHamburgerMenu() {
        findElementByLocator(By.cssSelector("div[class*='hamburger-icon--']")).click();
    }

    public WebElement donutElementByType(String type) {
        try {
            String xpath = String.format(".//div[contains(@class,'launchSuiteGrid__%s')]//*[@class='donut']", type);
            WebElement donutElement = findElementByLocator(By.xpath(xpath));
            return donutElement;
        } catch (NoSuchElementException e) {
            return null;
        }
    }

}
