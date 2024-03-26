package com.epam.reportportal.utils.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.lang.String.format;

public class PropertyReader {

    private static final Properties propertiesFromFile = readPropertyFromFile("project.properties");

    private PropertyReader() {

    }

    public static String gtProperty(String key, String defaultValue) {
        String property = getProperty(key);
        return property == null ? defaultValue : property;
    }

    public static String getProperty(String key){

        return propertiesFromFile.getProperty(key);
    }

    private static Properties readPropertyFromFile(String propertyFileName) {
        InputStream is = PropertyReader.class.getClassLoader().getResourceAsStream(propertyFileName);
        if (is == null){
            throw new RuntimeException(
                    format(
                            "Property file '%s' is not exist", propertyFileName));
        }
        Properties properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            throw new RuntimeException(format("Can't load properties from file: '%s'", propertyFileName), e);
        }

        return properties;
    }
}
