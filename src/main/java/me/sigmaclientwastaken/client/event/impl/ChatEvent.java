package me.sigmaclientwastaken.client.event.impl;

import me.sigmaclientwastaken.client.event.CancellableEvent;

public class ChatEvent extends CancellableEvent {

    private String message;

    public ChatEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
