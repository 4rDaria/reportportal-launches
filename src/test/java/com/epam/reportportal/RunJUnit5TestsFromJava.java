package com.epam.reportportal;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestPlan;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;

import static org.junit.platform.engine.discovery.ClassNameFilter.includeClassNamePatterns;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;

public class RunJUnit5TestsFromJava {

    SummaryGeneratingListener listener = new SummaryGeneratingListener();
    public void runAll() {
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(selectPackage("com.epam.reportportal.api"))
                .selectors(selectPackage("com.epam.reportportal.ui"))
                .filters(includeClassNamePatterns(".*Test"))
                .build();
        Launcher launcher = LauncherFactory.create();
        TestPlan testPlan = launcher.discover(request);
        launcher.registerTestExecutionListeners(listener);
        launcher.execute(request);
    }
}
