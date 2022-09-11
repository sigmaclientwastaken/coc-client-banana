package me.sigmaclientwastaken.client.gui.comp;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

public abstract class Component {

    protected final MinecraftClient mc = MinecraftClient.getInstance();

    public abstract void draw(int x, int y, MatrixStack stack);
    public abstract void click(int x, int y, int mouseX, int mouseY, int mouseButton);
    public abstract void key(int key);

}
