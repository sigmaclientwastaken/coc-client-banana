package me.sigmaclientwastaken.client.gui.window;

import me.sigmaclientwastaken.client.Client;
import me.sigmaclientwastaken.client.gui.ClickGuiScreen;
import me.sigmaclientwastaken.client.gui.comp.Component;
import me.sigmaclientwastaken.client.gui.comp.impl.ModuleComponent;
import me.sigmaclientwastaken.client.module.Category;
import me.sigmaclientwastaken.client.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

import java.util.ArrayList;
import java.util.List;

public class Window extends DrawableHelper {

    private final int width = 110, height = 13;

    private final String name;
    private boolean expanded;
    private int x, y;

    private final ClickGuiScreen parent;

    private final List<Component> comps = new ArrayList<>();

    public Window(String name, ClickGuiScreen parent) {
        this.name = name;
        this.parent = parent;
    }

    public Window(Category category, ClickGuiScreen parent) {
        this.name = category.toString();
        this.parent = parent;

        for(Module module : Client.getInstance().getModuleManager().getModules(category)) {
            comps.add(new ModuleComponent(module));
        }
    }

    public void draw(MatrixStack stack) {

        DrawableHelper.fill(stack, x-2, y, x+width+2, y+height, 0xff353535);

        MinecraftClient.getInstance().textRenderer.drawWithShadow(stack, name, x+5, y+2, -1);

        int clickY = y+height;
        int fullY = clickY + height*comps.size();

        if(expanded) {
            DrawableHelper.fill(stack, x, clickY, x+width, fullY, 0xff525252);

            for(Component comp : comps) {
                comp.draw(x, clickY, stack);
                clickY += height;
            }
        }

    }

    public void click(int mouseX, int mouseY, int mouseButton) {
        if(mouseX > x-2 && mouseX < x+width+2 && mouseY > y && mouseY < y+height) {
            switch (mouseButton) {
                case 0 -> {
                    parent.setDragging(this);
                    parent.setX(mouseX - x);
                    parent.setY(mouseY - y);
                }
                case 1 -> expanded = !expanded;
            }
        }

        int clickY = y+height;
        if(expanded) {
            for(Component comp : comps) {
                comp.click(x, clickY, mouseX, mouseY, mouseButton);
                clickY += height;
            }
        }
    }

    public void key(int key) {
        if(expanded) {
            for(Component comp : comps) {
                comp.key(key);
            }
        }
    }

    public String getName() {
        return name;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
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

    public ClickGuiScreen getParent() {
        return parent;
    }

    public List<Component> getComps() {
        return comps;
    }

}
