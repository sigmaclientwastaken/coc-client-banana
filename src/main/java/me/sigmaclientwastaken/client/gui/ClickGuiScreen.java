package me.sigmaclientwastaken.client.gui;

import me.sigmaclientwastaken.client.gui.window.Window;
import me.sigmaclientwastaken.client.module.Category;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class ClickGuiScreen extends Screen {

    private Window dragging;
    private int x, y;

    private final List<Window> windows = new ArrayList<>();

    public ClickGuiScreen() {
        super(new LiteralText("Click Gui"));

        for(Category category : Category.values()) {
            windows.add(new Window(category, this));
        }
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {

        for (Window window : windows) {
            window.click((int) mouseX, (int) mouseY, button);
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {

        if(button == 0) {
            dragging = null;
        }

        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {

        if(dragging != null) {
            dragging.setX(mouseX-x);
            dragging.setY(mouseY-y);
        }

        DrawableHelper.fill(matrices, 0, 0, width, height, 0x35000000);

        for(Window window : windows) {
            window.draw(matrices);
        }

        super.render(matrices, mouseX, mouseY, delta);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {

        for (Window window : windows) {
            window.key(keyCode);
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    public Window getDragging() {
        return dragging;
    }

    public void setDragging(Window dragging) {
        this.dragging = dragging;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public List<Window> getWindows() {
        return windows;
    }

}
