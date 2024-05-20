package com.epam.reportportal.pages.common;

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
}
