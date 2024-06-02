package com.epam.reportportal.utils.configuration;

import static com.epam.reportportal.utils.configuration.PropertyReader.getProperty;

public class EnvironmentConfiguration {
    public static final String BASE_URL = "baseUrl";
    public static final String PROJECT = "project.name";
    public static final String BROWSER = "browser";

    public static String baseUrlForCurrentEnv(){
        return getProperty(BASE_URL);
    }

    public static String projectNameForCurrentEnv(){
        return getProperty(PROJECT);
    }
    public static String browser(){
        return getProperty(BROWSER);
    }
}