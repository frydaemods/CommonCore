package dev.frydae.commoncore.events;

import dev.frydae.commoncore.user.RegisteredUser;
import net.minecraft.text.Text;

public final class ServerPlayerConnectionEvents {

    public static Event<DisconnectMessageCallback> DISCONNECT_MESSAGE = new Event<>(DisconnectMessageCallback.class, (listeners) -> (user) -> {
        Text response = null;

        for (DisconnectMessageCallback listener : listeners) {
            Text hold = listener.onDisconnectMessage(user);

            if (response == null && hold != null) {
                response = hold;
            }
        }

        return response;
    });

    public static Event<JoinMessageCallback> JOIN_MESSAGE = new Event<>(JoinMessageCallback.class, (listeners) -> (user) -> {
        Text response = null;

        for (JoinMessageCallback listener : listeners) {
            Text hold = listener.onJoinMessage(user);

            if (response == null && hold != null) {
                response = hold;
            }
        }

        return response;
    });

    @FunctionalInterface
    public interface DisconnectMessageCallback {
        Text onDisconnectMessage(RegisteredUser user) throws InterruptedException;
    }

    @FunctionalInterface
    public interface JoinMessageCallback {
        Text onJoinMessage(RegisteredUser user) throws InterruptedException;
    }
}
