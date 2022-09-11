package me.sigmaclientwastaken.client.mixin;

import me.sigmaclientwastaken.client.Client;
import me.sigmaclientwastaken.client.event.impl.Render2DEvent;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class MixinInGameHud {

    @Inject(method = "render", at = @At("HEAD"))
    public void render(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        Client.getInstance().getEventBus().post(new Render2DEvent(matrices));
    }

}
