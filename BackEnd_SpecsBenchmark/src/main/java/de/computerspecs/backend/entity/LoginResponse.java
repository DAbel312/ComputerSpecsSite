package de.computerspecs.backend.entity;

/**
 * Login response which has token for front-end
 */

public class LoginResponse {
    private String token;

    public LoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}

