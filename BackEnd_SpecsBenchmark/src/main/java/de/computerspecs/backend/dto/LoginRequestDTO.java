package de.computerspecs.backend.dto;

/**
 * LoginRequestDTO which represents the login request from front-end
 */

public class LoginRequestDTO {

    private String password;

    private String username;

    public LoginRequestDTO(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public LoginRequestDTO(){}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
