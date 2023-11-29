package dev.frydae.commoncore.mixin;

import dev.frydae.commoncore.events.ServerPlayerConnectionEvents;
import dev.frydae.commoncore.user.RegisteredUser;
import dev.frydae.commoncore.user.UserManager;
import dev.frydae.commoncore.utils.Utils;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {
    @Shadow
    public ServerPlayerEntity player;

    @Redirect(
            method = "onDisconnected",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/PlayerManager;broadcast(Lnet/minecraft/text/Text;Z)V"))
    public void broadcast(PlayerManager instance, Text message, boolean overlay) {
        RegisteredUser user = UserManager.getUser(player);

        Text text = null;

        try {
            text = ServerPlayerConnectionEvents.DISCONNECT_MESSAGE.getInvoker().onDisconnectMessage(user);
        } catch (InterruptedException e) {
            return;
        }

        if (text != null) {
            Utils.broadcastMessage(text);
        }
    }
}
