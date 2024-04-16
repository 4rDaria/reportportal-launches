package com.epam.reportportal.ui;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("com/epam/reportportal/ui/stepDefinitions")
@ConfigurationParameter(key = FEATURES_PROPERTY_NAME,value = "src/test/resources/features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME,
        value = "com/epam/reportportal/ui/stepDefinitions, " +
                "com/epam/reportportal/ui/hooks")
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME,value = "@ui")
@ConfigurationParameter(key = EXECUTION_DRY_RUN_PROPERTY_NAME,value = "false")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME,
        value = "pretty, " +
                "html:target/cucumber-report/cucumber_report.html, " +
                "json:target/cucumber-report/json/cucumber_report.json," +
                "junit:target/cucumber-report/junit/cucumber_report.xml"
)

public class TestRunner {
}
