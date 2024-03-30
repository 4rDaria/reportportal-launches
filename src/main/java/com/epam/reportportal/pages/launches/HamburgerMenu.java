package com.epam.reportportal.pages.launches;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.epam.reportportal.pages.common.ModalWindow;
import com.epam.reportportal.pages.common.ElWrapper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class HamburgerMenu extends ElWrapper {

    public HamburgerMenu(SelenideElement element) {
        super(element);
    }

    public ElementsCollection menuItems() {
        return $$("getMenuItems");
    }

    public SelenideElement menuItemByAction() {
        return $("getMenuItemByAction");
    }

    public ModalWindow deleteLaunchModal() {
        return new ModalWindow($("deleteLaunchModal"));
    }

    public void deleteLaunch() {

    }
}
