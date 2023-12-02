package dev.frydae.beguild.events;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.Vec3d;

public final class PlayerEvents {
    public static Event<PlayerMoveEventCallback> PLAYER_MOVE_EVENT = new Event<>(PlayerMoveEventCallback.class, (listeners) -> (player, from, to) -> {
        for (PlayerMoveEventCallback listener : listeners) {
            ActionResult result = listener.onPlayerMove(player, from, to);

            if (result != ActionResult.PASS) {
                return result;
            }
        }

        return ActionResult.PASS;
    });

    @FunctionalInterface
    public interface PlayerMoveEventCallback {
        ActionResult onPlayerMove(ServerPlayerEntity player, Vec3d from, Vec3d to);
    }
}
