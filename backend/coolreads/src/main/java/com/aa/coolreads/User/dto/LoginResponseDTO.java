package com.aa.coolreads.User.dto;

public class LoginResponseDTO {

    private String email;

    private String token;

    private long expiresIn;

    public LoginResponseDTO(String email, String token, long expiresIn) {
        this.email = email;
        this.token = token;
        this.expiresIn = expiresIn;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
