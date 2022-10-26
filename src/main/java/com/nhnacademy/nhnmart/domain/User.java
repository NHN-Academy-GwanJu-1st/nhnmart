package com.nhnacademy.nhnmart.domain;

import lombok.Data;
import lombok.Getter;

@Data
public class User {
    private String id;
    private String password;
    private int money;

    public User(String id, String password) {
        this.id = id;
        this.password = password;
        this.money = 20000;
    }
}
