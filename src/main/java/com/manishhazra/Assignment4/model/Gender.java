package com.manishhazra.Assignment4.model;

public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    OTHERS("Others");

    private final String displayName;

    private Gender(String displayName)
    {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
