package com.epam.reportportal.model.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
