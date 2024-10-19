package com.app.apekade.Model.Dto;

import com.app.apekade.Model.User;

import java.io.Serializable;

public class LoginResDto implements Serializable {
    private String access_token;
    private User user;
    private String role;

    public LoginResDto(String accessToken, User user, String role) {
        this.access_token = accessToken;
        this.user = user;
        this.role = role;
    }

    // Getters
    public String getAccessToken() {
        return access_token;
    }

    public User getUser() {
        return user;
    }

    public String getRole() {
        return role;
    }

    public void setAccessToken(String accessToken) {
        this.access_token = accessToken;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
