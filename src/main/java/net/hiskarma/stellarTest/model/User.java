package net.hiskarma.stellarTest.model;

import lombok.Data;

public class User {
    private Long id;
    private String name;

    protected User() {}

    public User(String name) {
        this.name = name;
    }
}
