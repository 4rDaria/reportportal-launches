package com.epam.reportportal;

import org.junit.platform.launcher.listeners.TestExecutionSummary;

import java.io.PrintWriter;


public class TestRunner {
    public static void main(String[] args) {

        RunJUnit5TestsFromJava runner = new RunJUnit5TestsFromJava();
        runner.runAll();

        TestExecutionSummary summary = runner.listener.getSummary();
        summary.printTo(new PrintWriter(System.out));

    }
}
