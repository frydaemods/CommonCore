package dev.frydae.commoncore.mixin;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import dev.frydae.commoncore.CommonCore;
import dev.frydae.commoncore.events.ServerPlayerConnectionEvents;
import dev.frydae.commoncore.user.RegisteredUser;
import dev.frydae.commoncore.user.UserManager;
import dev.frydae.commoncore.utils.Util;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.PlayerListS2CPacket;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;
import java.util.UUID;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {
    @Shadow @Final private List<ServerPlayerEntity> players;

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
            Util.broadcastMessage(text);
        }
    }

    @WrapWithCondition(
            method = "onPlayerConnect",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/PlayerManager;sendToAll(Lnet/minecraft/network/packet/Packet;)V"))
    public boolean sendToAllRedirect(PlayerManager instance, Packet<?> packet) {
        // This method replaces the one that sends player list entries to everyone

        if (packet instanceof PlayerListS2CPacket newPacket) {
            UUID uuid = newPacket.getEntries().get(0).profileId();
            ServerPlayerEntity playerToSend = CommonCore.getSingleton().getServer().getPlayerManager().getPlayer(uuid);

            List<ServerPlayerEntity> playersToSendTo = ServerPlayerConnectionEvents.SEND_PLAYER_LIST_FILTER.getInvoker().onSendPlayerListFilter(newPacket, playerToSend, players);

            playersToSendTo.forEach(p -> p.networkHandler.sendPacket(newPacket));

            return false;
        }

        return true;
    }
}
