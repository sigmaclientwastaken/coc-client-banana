package me.sigmaclientwastaken.client.event.impl;

import me.sigmaclientwastaken.client.event.Event;

public class KeyEvent extends Event {

    private final int key;

    public KeyEvent(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

}
