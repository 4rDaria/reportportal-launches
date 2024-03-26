package com.epam.reportportal.pages.launches;

import com.codeborne.selenide.SelenideElement;
import com.epam.reportportal.pages.common.ElWrapper;

import static com.codeborne.selenide.Selenide.$;

public class GridRow extends ElWrapper {

    public GridRow(SelenideElement element) {
        super(element);
    }

    public String getLaunchId() {
        return "getLaunchId";
    }

    public SelenideElement getCategory() {
        return $("getCategory");
    }

    public SelenideElement getCategoryCount() {
        return $("getCategoryCount");
    }

    public String getStartTime() {
        return "2024-03-24T22:45:41.857Z";
    }

    public SelenideElement getCheckbox() {
        return $("getCheckbox");
    }

    public HamburgerMenu hamburgerMenu() {
        return new HamburgerMenu($("HamburgerMenu"));
    }

    public SelenideElement donutElementByType() {
        return $("donutElementByType");
    }
}
