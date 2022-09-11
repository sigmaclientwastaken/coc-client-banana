package me.sigmaclientwastaken.client.module.impl.render;

import me.sigmaclientwastaken.client.gui.ClickGuiScreen;
import me.sigmaclientwastaken.client.module.Category;
import me.sigmaclientwastaken.client.module.Module;
import me.sigmaclientwastaken.client.module.ModuleInfo;

@ModuleInfo(name = "ClickGui", category = Category.RENDER, key = 344 /* rshift key code */)
public class ClickGui extends Module {

    private ClickGuiScreen screen;

    @Override
    public void onEnable() {
        if(screen == null)
            screen = new ClickGuiScreen();

        mc.setScreenAndRender(screen);

        setEnabled(false);
    }

}
