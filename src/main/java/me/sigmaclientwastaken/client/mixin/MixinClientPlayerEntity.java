package me.sigmaclientwastaken.client.mixin;

import me.sigmaclientwastaken.client.Client;
import me.sigmaclientwastaken.client.event.impl.ChatEvent;
import me.sigmaclientwastaken.client.event.impl.TickEvent;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class MixinClientPlayerEntity {

    @Inject(method = "sendMovementPackets", at = @At("HEAD"))
    public void sendMovementPackets(CallbackInfo ci) {
        Client.getInstance().getEventBus().post(new TickEvent());
    }

    @Inject(method = "sendChatMessage", at = @At("HEAD"), cancellable = true)
    public void sendChatMessage(String message, CallbackInfo ci) {
        ChatEvent e = new ChatEvent(message);
        Client.getInstance().getEventBus().post(e);

        message = e.getMessage();

        if(e.isCancelled())
            ci.cancel();
    }

}
