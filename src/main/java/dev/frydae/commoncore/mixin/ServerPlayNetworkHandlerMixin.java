package dev.frydae.commoncore.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import dev.frydae.commoncore.events.PlayerEvents;
import dev.frydae.commoncore.events.ServerPlayerConnectionEvents;
import dev.frydae.commoncore.user.RegisteredUser;
import dev.frydae.commoncore.user.UserManager;
import dev.frydae.commoncore.utils.Util;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Debug(export = true)
@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {
    @Shadow public ServerPlayerEntity player;
    @Shadow private double updatedX;
    @Shadow private double updatedY;
    @Shadow private double updatedZ;

    @Redirect(
            method = "cleanUp",
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
            Util.broadcastMessage(text);
        }
    }

    @Inject(
            method = "onPlayerMove",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;move(Lnet/minecraft/entity/MovementType;Lnet/minecraft/util/math/Vec3d;)V"),
            cancellable = true
    )
    public void move(PlayerMoveC2SPacket packet, CallbackInfo ci,
                     @Local(ordinal = 0) double x, @Local(ordinal = 1) double y, @Local(ordinal = 2) double z) {
        int oldX = (int) player.getX();
        int oldY = (int) player.getY();
        int oldZ = (int) player.getZ();

        int newX = (int) x;
        int newY = (int) y;
        int newZ = (int) z;

        if (!Objects.equals(oldX, newX) || !Objects.equals(oldY, newY) || !Objects.equals(oldZ, newZ)) {
            ActionResult result = PlayerEvents.PLAYER_MOVE_EVENT.getInvoker().onPlayerMove(player, new Vec3d(oldX, oldY, oldZ), new Vec3d(newX, newY, newZ));

            if (result != ActionResult.PASS) {
                ci.cancel();

                player.teleport(player.getX(), player.getY(), player.getZ());
            }
        }
    }
}
