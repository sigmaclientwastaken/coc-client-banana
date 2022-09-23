package me.sigmaclientwastaken.client.command.impl;

import com.google.common.eventbus.Subscribe;
import me.sigmaclientwastaken.client.Client;
import me.sigmaclientwastaken.client.command.Command;
import me.sigmaclientwastaken.client.command.CommandHandler;
import me.sigmaclientwastaken.client.event.impl.Render2DEvent;
import me.sigmaclientwastaken.client.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;

public class BindCommand extends Command {

    public BindCommand() {
        super("Bind", ".bind <module>");
        Client.getInstance().getEventBus().register(this);
    }

    Module bind;

    @Subscribe
    public void onRender2D(Render2DEvent e) {
        if(MinecraftClient.getInstance().currentScreen == null && bind != null) {
            MinecraftClient.getInstance().setScreen(new BindScreen(bind));
            bind = null;
        }
    }

    @Override
    public boolean handle(String[] args) {
        if(args.length != 2) {
            return false;
        }

        Module module = Client.getInstance().getModuleManager().getModule(args[1]);

        if(module == null) {
            CommandHandler.message(Formatting.RED + "Module not found.");
        } else {
            // who would have thought that currentScreen is set to null to close chat
            // fuck you, coc client banana reviewers
            bind = module;
        }

        return true;
    }

}

class BindScreen extends Screen {

    private final Module module;
    boolean bound;

    int brain;

    protected BindScreen(Module module) {
        super(new LiteralText("Bind Screen"));
        this.module = module;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {

        DrawableHelper.fill(matrices, 0, 0, width, height, 0x35000000);
        DrawableHelper.drawStringWithShadow(matrices, MinecraftClient.getInstance().textRenderer, "Press any key to bind...", width/2 -
                MinecraftClient.getInstance().textRenderer.getWidth("Press any key to bind...")/2, height/3, -1);

        brain++;

        super.render(matrices, mouseX, mouseY, delta);
    }

    @Override
    public void close() {

        if(brain < 20)
            return;

        if(!bound) {
            module.setKey(0);
        }

        super.close();
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {

        if(brain < 20)
            return super.keyPressed(keyCode, scanCode, modifiers);

        bound = true;
        module.setKey(keyCode);
        close();

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

}
