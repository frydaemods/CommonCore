package dev.frydae.beguild.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.MinecraftServer;

public final class ServerLifecycleEvents {
    private ServerLifecycleEvents() {
    }

    public static final Event<ServerStarting> EARLIEST_SERVER_STARTING = EventFactory.createArrayBacked(ServerStarting.class, callbacks -> server -> {
        for (ServerStarting callback : callbacks) {
            callback.onServerStarting(server);
        }
    });
    public static final Event<ServerStarting> PRE_SERVER_STARTING = EventFactory.createArrayBacked(ServerStarting.class, callbacks -> server -> {
        for (ServerStarting callback : callbacks) {
            callback.onServerStarting(server);
        }
    });
    public static final Event<ServerStarting> SERVER_STARTING = EventFactory.createArrayBacked(ServerStarting.class, callbacks -> server -> {
        for (ServerStarting callback : callbacks) {
            callback.onServerStarting(server);
        }
    });
    public static final Event<ServerStarting> POST_SERVER_STARTING = EventFactory.createArrayBacked(ServerStarting.class, callbacks -> server -> {
        for (ServerStarting callback : callbacks) {
            callback.onServerStarting(server);
        }
    });

    public static final Event<ServerStarted> PRE_SERVER_STARTED = EventFactory.createArrayBacked(ServerStarted.class, callbacks -> server -> {
        for (ServerStarted callback : callbacks) {
            callback.onServerStarted(server);
        }
    });
    public static final Event<ServerStarted> SERVER_STARTED = EventFactory.createArrayBacked(ServerStarted.class, (callbacks) -> (server) -> {
        for (ServerStarted callback : callbacks) {
            callback.onServerStarted(server);
        }
    });
    public static final Event<ServerStarted> POST_SERVER_STARTED = EventFactory.createArrayBacked(ServerStarted.class, callbacks -> server -> {
        for (ServerStarted callback : callbacks) {
            callback.onServerStarted(server);
        }
    });

    public static final Event<ServerStopping> PRE_SERVER_STOPPING = EventFactory.createArrayBacked(ServerStopping.class, callbacks -> server -> {
        for (ServerStopping callback : callbacks) {
            callback.onServerStopping(server);
        }
    });
    public static final Event<ServerStopping> SERVER_STOPPING = EventFactory.createArrayBacked(ServerStopping.class, (callbacks) -> (server) -> {
        for (ServerStopping callback : callbacks) {
            callback.onServerStopping(server);
        }
    });
    public static final Event<ServerStopping> POST_SERVER_STOPPING = EventFactory.createArrayBacked(ServerStopping.class, callbacks -> server -> {
        for (ServerStopping callback : callbacks) {
            callback.onServerStopping(server);
        }
    });

    public static final Event<ServerStopped> PRE_SERVER_STOPPED = EventFactory.createArrayBacked(ServerStopped.class, callbacks -> server -> {
        for (ServerStopped callback : callbacks) {
            callback.onServerStopped(server);
        }
    });
    public static final Event<ServerStopped> SERVER_STOPPED = EventFactory.createArrayBacked(ServerStopped.class, callbacks -> server -> {
        for (ServerStopped callback : callbacks) {
            callback.onServerStopped(server);
        }
    });
    public static final Event<ServerStopped> POST_SERVER_STOPPED = EventFactory.createArrayBacked(ServerStopped.class, callbacks -> server -> {
        for (ServerStopped callback : callbacks) {
            callback.onServerStopped(server);
        }
    });

    @FunctionalInterface
    public interface ServerStarting {
        void onServerStarting(MinecraftServer server);
    }

    @FunctionalInterface
    public interface ServerStarted {
        void onServerStarted(MinecraftServer server);
    }

    @FunctionalInterface
    public interface ServerStopping {
        void onServerStopping(MinecraftServer server);
    }

    @FunctionalInterface
    public interface ServerStopped {
        void onServerStopped(MinecraftServer server);
    }
}
