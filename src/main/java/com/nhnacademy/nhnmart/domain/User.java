package com.nhnacademy.nhnmart.domain;

import lombok.Data;

@Data
public class User {
    private String id;
    private String password;
    private int moeny;

    public User(String id, String password) {
        this.id = id;
        this.password = password;
        moeny = 20000;
    }
}
