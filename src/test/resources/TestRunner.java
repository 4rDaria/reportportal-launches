package com.epam.reportportal;

import org.testng.TestNG;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {

    public static void main(String[] args) {

        TestNG testNG = new TestNG();

        List<String> suiteFiles = new ArrayList<>();
        suiteFiles.add("src/test/resources/testng.xml");

        testNG.setTestSuites(suiteFiles);

        testNG.run();
    }

}