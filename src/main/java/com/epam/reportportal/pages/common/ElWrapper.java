package com.epam.reportportal.pages.common;

import com.codeborne.selenide.SelenideElement;

public abstract class ElWrapper{
    public SelenideElement element;

    public ElWrapper(SelenideElement element){
        this.element = element;
    }

    public ElWrapper(ElWrapper element){
        this.element = element.el();
    }

    public SelenideElement el(){
        return element;
    }
}
