package me.sigmaclientwastaken.client.module;

import com.google.common.eventbus.Subscribe;
import me.sigmaclientwastaken.client.Client;
import me.sigmaclientwastaken.client.event.impl.KeyEvent;
import me.sigmaclientwastaken.client.module.impl.movement.Sprint;
import me.sigmaclientwastaken.client.module.impl.render.ClickGui;
import me.sigmaclientwastaken.client.module.impl.render.HUD;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager {

    private final List<Module> modules = new ArrayList<>();

    public ModuleManager() {

        // add modules here
        modules.add(new HUD());
        modules.add(new ClickGui());
        modules.add(new Sprint());

        Client.getInstance().getEventBus().register(this);
    }

    public List<Module> getModules() {
        return modules;
    }

    public List<Module> getModules(Category c) {
        return modules.stream().filter(m -> m.getCategory() == c).collect(Collectors.toList());
    }

    public Module getModule(String name) {
        return modules.stream().filter(m -> m.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    @Subscribe
    public void onKey(KeyEvent e) {
        modules.stream().filter(m -> m.getKey() == e.getKey()).forEach(m -> m.setEnabled(!m.isEnabled()));
    }

}
