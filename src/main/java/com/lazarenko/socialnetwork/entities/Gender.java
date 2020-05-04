package com.lazarenko.socialnetwork.entities;

public enum Gender {
    MALE("мужской"), FEMALE("женский");

    private String name;

    Gender(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
