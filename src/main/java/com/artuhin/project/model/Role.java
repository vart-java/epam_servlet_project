package com.artuhin.project.model;

public enum Role {
    ADMINISTRATOR(1),
    MASTER(2),
    CLIENT(3),
    GUEST(4);

    public int getId() {
        return id;
    }

    private final int id;

    Role(int id) {
        this.id=id;
    }

}
