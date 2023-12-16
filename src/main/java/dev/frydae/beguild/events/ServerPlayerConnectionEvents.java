package dev.frydae.beguild.events;

import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerListS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Collections;
import java.util.List;

public final class ServerPlayerConnectionEvents {
    public static Event<SpawnPacketFilterCallback> SPAWN_PACKET_FILTER = new Event<>(SpawnPacketFilterCallback.class, (listeners) -> (packet, target) -> {
        for (SpawnPacketFilterCallback listener : listeners) {
            if (!listener.onSpawnPacketSend(packet, target)) {
                return false;
            }
        }

        return true;
    });

    public static Event<SendPlayerListFilterCallback> SEND_PLAYER_LIST_FILTER = new Event<>(SendPlayerListFilterCallback.class, (listeners) -> (packet, playerToSend, players) -> {
        List<ServerPlayerEntity> playersToSendTo = Collections.synchronizedList(players);

        for (SendPlayerListFilterCallback listener : listeners) {
            List<ServerPlayerEntity> playersToRemove = listener.onSendPlayerListFilter(packet, playerToSend, playersToSendTo);

            playersToSendTo.removeAll(playersToRemove);
        }

        return playersToSendTo;
    });

    @FunctionalInterface
    public interface SpawnPacketFilterCallback {
        boolean onSpawnPacketSend(EntitySpawnS2CPacket packet, ServerPlayerEntity target);
    }

    @FunctionalInterface
    public interface SendPlayerListFilterCallback {
        List<ServerPlayerEntity> onSendPlayerListFilter(PlayerListS2CPacket packet, ServerPlayerEntity playerToSend, List<ServerPlayerEntity> playersToSendTo);
    }
}
