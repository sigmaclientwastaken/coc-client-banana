package me.sigmaclientwastaken.client.event.impl;

import me.sigmaclientwastaken.client.event.Event;
import net.minecraft.client.util.math.MatrixStack;

public class Render2DEvent extends Event {

    private MatrixStack stack;

    public Render2DEvent(MatrixStack stack) {
        this.stack = stack;
    }

    public MatrixStack getStack() {
        return stack;
    }

    public void setStack(MatrixStack stack) {
        this.stack = stack;
    }

}
