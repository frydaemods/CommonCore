package dev.frydae.beguild.events;

import net.minecraft.server.MinecraftServer;

public final class ServerEvents {

    public static Event<PostSingletonCallback> POST_SINGLETON = new Event<>(PostSingletonCallback.class, (listeners) -> (server) -> {
        for (PostSingletonCallback listener : listeners) {
            listener.onPostSingletonCallback(server);
        }
    });

    public interface PostSingletonCallback {
        void onPostSingletonCallback(MinecraftServer server);
    }
}
