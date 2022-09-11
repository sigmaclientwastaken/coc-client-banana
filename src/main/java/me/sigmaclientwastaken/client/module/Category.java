package me.sigmaclientwastaken.client.module;

public enum Category {
    RENDER("Render"),
    MOVEMENT("Movement");

    String name;

    Category(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
