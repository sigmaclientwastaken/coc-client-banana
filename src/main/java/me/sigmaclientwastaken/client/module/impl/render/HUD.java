package me.sigmaclientwastaken.client.module.impl.render;

import com.google.common.eventbus.Subscribe;
import me.sigmaclientwastaken.client.Client;
import me.sigmaclientwastaken.client.event.impl.Render2DEvent;
import me.sigmaclientwastaken.client.module.Category;
import me.sigmaclientwastaken.client.module.Module;
import me.sigmaclientwastaken.client.module.ModuleInfo;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.text.LiteralText;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@ModuleInfo(name = "HUD", category = Category.RENDER, enabled = true)
public class HUD extends Module {

    @Subscribe
    public void onRender2D(Render2DEvent e) {

        TextRenderer fr = mc.textRenderer;

        { // watermark

            String watermark = Client.getInstance().getClientName() + " " + Client.getInstance().getClientBuild();

            fr.drawWithShadow(e.getStack(), new LiteralText(watermark), 4, 4, -1);

        }

        { // arraylist

            List<Module> enabledModules = Client.getInstance().getModuleManager().getModules().stream().filter(Module::isEnabled).sorted(Comparator.comparingInt(m ->
                    fr.getWidth(((Module) m).getName())).reversed()).collect(Collectors.toList());

            int i = 0;
            for(Module module : enabledModules) {

                fr.drawWithShadow(e.getStack(), module.getName(), 5.5F, 16 + (i*(fr.fontHeight+2))-1, -1);

                i++;
            }

        }

    }

}
