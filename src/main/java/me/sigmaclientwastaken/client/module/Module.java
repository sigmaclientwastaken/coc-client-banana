package me.sigmaclientwastaken.client.module;

import me.sigmaclientwastaken.client.Client;
import net.minecraft.client.MinecraftClient;

public class Module {

    protected final MinecraftClient mc = MinecraftClient.getInstance();

    private final String name;
    private final Category category;
    private int key;
    private boolean enabled;

    public Module() {
        if(!this.getClass().isAnnotationPresent(ModuleInfo.class))
            throw new RuntimeException("ModuleInfo annotation not found on class " +this.getClass().getName());

        ModuleInfo info = this.getClass().getAnnotation(ModuleInfo.class);

        name = info.name();
        category = info.category();
        key = info.key();
        enabled = info.enabled();

        if(enabled)
            Client.getInstance().getEventBus().register(this);

    }

    public void onEnable() {}
    public void onDisable() {}

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        if(this.enabled != enabled) {
            this.enabled = enabled;

            if(enabled) {
                onEnable();

                if(this.enabled)
                    Client.getInstance().getEventBus().register(this);
            } else {
                Client.getInstance().getEventBus().unregister(this);
                onDisable();
            }
        }
    }

}
