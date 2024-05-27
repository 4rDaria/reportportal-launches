package com.epam.reportportal.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static java.lang.String.format;

public class ModalWindow extends ElWrapper {
    public ModalWindow(WebElement element) {
        super(element);
    }

    public WebElement buttonWithText(String buttonText) {
        return element.findElement(By.xpath(String.format(".//button[contains(text(),'%s')]/..", buttonText)));
    }

}
