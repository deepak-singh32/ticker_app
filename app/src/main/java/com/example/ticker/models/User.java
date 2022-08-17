package com.example.ticker.models;

public class User {

    Integer user_id;


    public User(Integer user_id) {
        this.user_id = user_id;
    }

    public User() {
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
