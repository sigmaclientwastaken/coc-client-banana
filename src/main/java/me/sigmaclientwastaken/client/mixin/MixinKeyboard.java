package me.sigmaclientwastaken.client.mixin;

import me.sigmaclientwastaken.client.Client;
import me.sigmaclientwastaken.client.event.impl.KeyEvent;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class MixinKeyboard {

    @Shadow @Final private MinecraftClient client;

    @Inject(method = "onKey", at = @At("HEAD"))
    public void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        if (window == this.client.getWindow().getHandle() && action == 1 && client.currentScreen == null) {
            Client.getInstance().getEventBus().post(new KeyEvent(key));
        }
    }

}
