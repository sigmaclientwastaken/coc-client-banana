package me.sigmaclientwastaken.client.module.impl.movement;

import com.google.common.eventbus.Subscribe;
import me.sigmaclientwastaken.client.Client;
import me.sigmaclientwastaken.client.event.impl.Render2DEvent;
import me.sigmaclientwastaken.client.event.impl.TickEvent;
import me.sigmaclientwastaken.client.module.Category;
import me.sigmaclientwastaken.client.module.Module;
import me.sigmaclientwastaken.client.module.ModuleInfo;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.text.LiteralText;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@ModuleInfo(name = "Sprint", category = Category.MOVEMENT)
public class Sprint extends Module {

    @Subscribe
    public void onTick(TickEvent e) {

        assert mc.player != null;
        mc.player.setSprinting(true);

    }

}
