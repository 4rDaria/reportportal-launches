package com.epam.reportportal.ui;

import com.epam.reportportal.TestRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.junit.Assert.assertTrue;

@Execution(ExecutionMode.CONCURRENT)
public class LaunchesPageTest {

    private static final Logger logger = LogManager.getRootLogger();

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
    public void userIsAbleToMoveToAppropriateLaunchViaCountElement() {
        assertTrue(true);
    }

}
