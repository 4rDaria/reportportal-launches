package com.epam.reportportal.pages.launches;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class LaunchesPage {

    public ElementsCollection gridRowElements() {
        return $$("div[class*='grid-row-wrapper']");
    }

    public ElementsCollection titles() {
        return $$("[class*='title-full']");
    }

    public ActionMenu openActionMenu() {
        $("div[class*='ghost-menu-button']").click();
        return new ActionMenu($("div[class*='ghostMenuButton__menu--']"));
    }

}
