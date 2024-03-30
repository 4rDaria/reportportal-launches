package com.epam.reportportal.pages.common;

import com.codeborne.selenide.SelenideElement;

import static java.lang.String.format;

public class ModalWindow extends ElWrapper {
    public ModalWindow(SelenideElement element) {
        super(element);
    }

    public SelenideElement buttonWithText(String buttonText) {
        return element.$x(format(".//button[contains(text(),'%s')]/..", buttonText));
    }

}
