package com.epam.reportportal.services;

import com.epam.reportportal.model.user.User;

import static com.epam.reportportal.utils.configuration.PropertyReader.getProperty;

public class UserCreator {
    public static final String ADMIN_USER_NAME = "superadmin.user.name";
    public static final String ADMIN_USER_PASSWORD = "superadmin.user.password";
    public static final String DEFAULT_USER_NAME = "default.user.name";
    public static final String DEFAULT_USER_PASSWORD = "default.user.password";

    public static User adminUser() {
        return new User(getProperty(ADMIN_USER_NAME), getProperty(ADMIN_USER_PASSWORD));
    }

    public static User defaultUser() {
        return new User(getProperty(DEFAULT_USER_NAME), getProperty(DEFAULT_USER_PASSWORD));
    }

}

