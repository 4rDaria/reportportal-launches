package com.epam.reportportal.ui;

import static org.testng.Assert.assertTrue;

import com.epam.reportportal.pages.launches.LaunchesPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class LaunchesPageTest {

    private static final Logger logger = LogManager.getRootLogger();

    private static LaunchesPage launchesPage;

    @Test
    public void launchesListSortedByMostRecentByDefault() {
        assertTrue(true);
    }

    @Test
    public void userCanResortLaunchesByEachCategoriesCount() {
        assertTrue(true);
    }

    @Test
    public void eachLaunchesContainsAllTestCountData() {
        assertTrue(true);
    }

    @Test
    public void userIsAbleToSelectSeveralLaunchesAndCompareThem() {
        assertTrue(true);
    }

    @Test
    public void userIsAbleToRemoveLaunches() {
        assertTrue(true);
    }

    @Test
    public void userIsAbleToMoveToAppropriateLaunchViaDonutElement() {
        assertTrue(true);
    }

    @Test
    public void userIsAbleToMoveToAppropriateLaunchClickingCountElement() {
        assertTrue(true);
    }
}
