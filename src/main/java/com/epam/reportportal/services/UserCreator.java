package com.epam.reportportal.services;

import com.epam.reportportal.model.user.User;

import static com.epam.reportportal.utils.configuration.PropertyReader.getProperty;

public class UserCreator {
    public static final String TESTDATA_USER_NAME = "testdata.user.name";
    public static final String TESTDATA_USER_PASSWORD = "testdata.user.password";

    public static User withCredentialsFromProperty(){
        return new User(getProperty(TESTDATA_USER_NAME),
                getProperty(TESTDATA_USER_PASSWORD));
    }
}

