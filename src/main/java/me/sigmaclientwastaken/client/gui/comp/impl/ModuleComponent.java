package me.sigmaclientwastaken.client.gui.comp.impl;

import me.sigmaclientwastaken.client.gui.comp.Component;
import me.sigmaclientwastaken.client.module.Module;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;

public class ModuleComponent extends Component {

    private final Module module;
    private boolean binding;

    public ModuleComponent(Module module) {
        this.module = module;
    }

    @Override
    public void draw(int x, int y, MatrixStack stack) {

        TextRenderer fr = mc.textRenderer;

        if(binding) {
            fr.drawWithShadow(stack, "Binding...", x + 1, y + 2, -1);
        } else {
            fr.drawWithShadow(stack, module.getName(), x + 1, y + 2, module.isEnabled()? 0xff2090AA : -1);
        }

    }

    @Override
    public void click(int x, int y, int mouseX, int mouseY, int mouseButton) {
        if(mouseX > x && mouseX < x+110 && mouseY > y && mouseY < y+13) {
            switch (mouseButton) {
                case 2 -> binding = !binding;
                case 0 -> module.setEnabled(!module.isEnabled());
            }
        } else {
            binding = false;
        }
    }

    @Override
    public void key(int key) {
        if(binding) {
            module.setKey(key);
            binding = false;
        }
    }

}
