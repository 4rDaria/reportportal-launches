package com.epam.reportportal.pages.launches;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class LaunchesPage {

    public static ElementsCollection gridRowElements() {
        return $$("gridRowElements");
    }

    public static ElementsCollection titles() {
        return $$("titles");
    }

    public static ActionMenu openActionMenu() {
        return new ActionMenu($("openActionMenu"));
    }

}
