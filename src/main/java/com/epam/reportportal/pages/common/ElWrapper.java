package com.epam.reportportal.pages.common;

import com.epam.reportportal.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class ElWrapper{
    public WebElement element;

    public ElWrapper(WebElement element) {
        this.element = element;
    }

    public ElWrapper(ElWrapper element) {
        this.element = element.getWrappedElement();
    }

    public WebElement getWrappedElement() {
        return element;
    }

    public WebElement findElementByLocator(By locator){
        return element.findElement(locator);
    }

    public WebElement findAnotherElement(By locator){
        WebDriver driver = DriverManager.getDriver();
        return driver.findElement(locator);
    }

    public String getAttributeByName(String name) {
        return element.getAttribute("data-id");
    }
}
