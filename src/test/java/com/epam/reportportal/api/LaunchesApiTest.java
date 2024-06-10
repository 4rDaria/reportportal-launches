package com.epam.reportportal.api;

import com.epam.reportportal.junit5.ReportPortalExtension;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(ReportPortalExtension.class)
public class LaunchesApiTest {

    private static final Logger logger = LogManager.getRootLogger();


    @Test
    public void getLaunchesByFilters() {
        //prepare data
        //perform request
        //assert response.statusCode etc.
        logger.info("getLaunchesByFilters");
        assertTrue(true);
    }

    @Test
    public void getLaunchesByFilters() {
        //prepare data
        //perform request
        //assert response.statusCode etc.
        logger.info("getLaunchesByFilters");
        assertTrue(true);
    }

    @Test
    public void removeLaunchById() {
        //get Launch id for launch delete
        //perform request
        //assert response.statusCode etc.
        logger.info("removeLaunchById");
        assertTrue(true);
    }

    @Test
    public void startLaunchAnalysis() {
        //prepare data
        //perform request
        //assert response.statusCode etc.
        logger.info("startLaunchAnalysis");
        assertTrue(true);
    }

    @Test
    public void compareLaunches() {
        //prepare data
        //perform request
        //assert response.statusCode etc.
        logger.info("compareLaunches");
        assertTrue(true);
    }

    @Test
    public void forceFinishLaunch() {
        //prepare data
        //perform request
        //assert response.statusCode etc.
        logger.info("forceFinishLaunch");
        assertTrue(true);
    }

    @Test
    public void mergeLaunches() {
        //prepare data
        //perform request
        //assert response.statusCode etc.
        logger.info("mergeLaunches");
        assertTrue(true);
    }

}