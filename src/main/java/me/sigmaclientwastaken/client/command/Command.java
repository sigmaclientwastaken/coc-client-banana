package me.sigmaclientwastaken.client.command;

public abstract class Command {

    private final String name, syntax;

    protected Command(String name, String syntax) {
        this.name = name;
        this.syntax = syntax;
    }

    public abstract boolean handle(String[] args);

    public String getName() {
        return name;
    }

    public String getSyntax() {
        return syntax;
    }

}
