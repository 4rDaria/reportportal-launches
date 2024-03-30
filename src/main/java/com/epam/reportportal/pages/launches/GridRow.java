package com.epam.reportportal.pages.launches;

import com.codeborne.selenide.SelenideElement;
import com.epam.reportportal.pages.common.ElWrapper;

import static com.codeborne.selenide.Selenide.$;

public class GridRow extends ElWrapper {

    public GridRow(SelenideElement element) {
        super(element);
    }

    public String launchId() {
        return "getLaunchId";
    }

    public SelenideElement category() {
        return $("getCategory");
    }

    public SelenideElement categoryCount() {
        return $("getCategoryCount");
    }

    public String startTime() {
        return "2024-03-24T22:45:41.857Z";
    }

    public SelenideElement checkbox() {
        return $("getCheckbox");
    }

    public HamburgerMenu hamburgerMenu() {
        return new HamburgerMenu($("HamburgerMenu"));
    }

    public SelenideElement donutElementByType() {
        return $("donutElementByType");
    }
}
