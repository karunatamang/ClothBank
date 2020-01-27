package com.example.clothbank.response;

import com.example.clothbank.model.User;

public class LoginResponse {

    private String token;
    private User user;

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }
}
