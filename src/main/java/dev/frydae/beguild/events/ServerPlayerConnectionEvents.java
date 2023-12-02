package dev.frydae.beguild.events;

import dev.frydae.beguild.systems.LanguageLoader;
import dev.frydae.beguild.user.RegisteredUser;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerListS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.Collections;
import java.util.List;

public final class ServerPlayerConnectionEvents {

    public static Event<DisconnectMessageCallback> DISCONNECT_MESSAGE = new Event<>(DisconnectMessageCallback.class, (listeners) -> (user) -> {
        Text response = Text.literal(LanguageLoader.getString("multiplayer.player.left", user.getName())).formatted(Formatting.YELLOW);
        boolean responseSet = false;

        for (DisconnectMessageCallback listener : listeners) {
            Text hold = listener.onDisconnectMessage(user);

            if (!responseSet && hold != null) {
                response = hold;

                responseSet = true;
            }
        }

        return response;
    });

    public static Event<JoinMessageCallback> JOIN_MESSAGE = new Event<>(JoinMessageCallback.class, (listeners) -> (user) -> {
        Text response = Text.literal(LanguageLoader.getString("multiplayer.player.joined", user.getName())).formatted(Formatting.YELLOW);

        boolean responseSet = false;

        for (JoinMessageCallback listener : listeners) {
            Text hold = listener.onJoinMessage(user);

            if (!responseSet && hold != null) {
                response = hold;

                responseSet = true;
            }
        }

        return response;
    });

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
    public interface DisconnectMessageCallback {
        Text onDisconnectMessage(RegisteredUser user) throws InterruptedException;
    }

    @FunctionalInterface
    public interface JoinMessageCallback {
        Text onJoinMessage(RegisteredUser user) throws InterruptedException;
    }

    @FunctionalInterface
    public interface SpawnPacketFilterCallback {
        boolean onSpawnPacketSend(EntitySpawnS2CPacket packet, ServerPlayerEntity target);
    }

    @FunctionalInterface
    public interface SendPlayerListFilterCallback {
        List<ServerPlayerEntity> onSendPlayerListFilter(PlayerListS2CPacket packet, ServerPlayerEntity playerToSend, List<ServerPlayerEntity> playersToSendTo);
    }
}
