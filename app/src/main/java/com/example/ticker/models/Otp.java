package com.example.ticker.models;

public class Otp {

    String token;

    public Otp(String token) {
        this.token = token;
    }

    public Otp() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
