package dev.frydae.commoncore.mixin;

import dev.frydae.commoncore.events.ServerPlayerConnectionEvents;
import dev.frydae.commoncore.user.RegisteredUser;
import dev.frydae.commoncore.user.UserManager;
import dev.frydae.commoncore.utils.Utils;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {
    @Redirect(
            method = "onPlayerConnect",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/PlayerManager;broadcast(Lnet/minecraft/text/Text;Z)V"))
    public void broadcast(PlayerManager instance, Text message, boolean overlay, ClientConnection connection, ServerPlayerEntity player) {
        RegisteredUser user = UserManager.getUser(player);

        Text text = null;

        try {
            text = ServerPlayerConnectionEvents.JOIN_MESSAGE.getInvoker().onJoinMessage(user);
        } catch (InterruptedException e) {
            return;
        }

        if (text != null) {
            Utils.broadcastMessage(text);
        }
    }
}
