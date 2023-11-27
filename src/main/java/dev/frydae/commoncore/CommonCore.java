package dev.frydae.commoncore;

import lombok.Getter;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;

public class CommonCore implements DedicatedServerModInitializer {
    private static volatile CommonCore singleton;

    @Getter private MinecraftServer server;

    @Override
    public void onInitializeServer() {
        ServerLifecycleEvents.SERVER_STARTED.register((server) -> getSingleton().setServer(server));
    }

    private void setServer(MinecraftServer server) {
        this.server = server;
    }

    public static CommonCore getSingleton() {
        if (singleton == null) {
            synchronized (CommonCore.class) {
                if (singleton == null) {
                    singleton = new CommonCore();
                }
            }
        }

        return singleton;
    }
}
