package com.epam.reportportal.utils.configuration;

import static com.epam.reportportal.utils.configuration.PropertyReader.getProperty;

public class EnvironmentConfiguration {
    public static final String BASE_URL = "baseUrl";
    public static final String PROJECT = "project.name";
    public static final String TOKEN = "token";

    public static String baseUrlForCurrentEnv() {
        return getProperty(BASE_URL);
    }

    public static String projectNameForCurrentEnv() {
        return getProperty(PROJECT);
    }

    public static String barearToken() {
        return getProperty(TOKEN);
    }

    public static void setProxyProperties(){
        System.setProperty("proxySet","true");
        System.setProperty("proxyHost","127.0.0.1");
        System.setProperty("proxyPort","8866");
    }
}