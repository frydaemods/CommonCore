package dev.frydae.commoncore;

import co.aikar.taskchain.FabricTaskChainFactory;
import co.aikar.taskchain.TaskChain;
import co.aikar.taskchain.TaskChainFactory;
import dev.frydae.commoncore.commands.Commands;
import dev.frydae.commoncore.user.UserManager;
import lombok.Getter;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.MinecraftServer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CommonCore implements DedicatedServerModInitializer {
    private static volatile CommonCore singleton;

    @Getter private MinecraftServer server;
    private TaskChainFactory taskChainFactory;

    @Override
    public void onInitializeServer() {
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            UserManager.addOrUpdatePlayer(handler.getPlayer());
        });

        registerLifecycleEvents();

        Commands.registerCommands();
    }

    private void registerLifecycleEvents() {
        ServerLifecycleEvents.SERVER_STARTED.register((server) -> {
            getSingleton().setServer(server);
            getSingleton().taskChainFactory = FabricTaskChainFactory.create(server);

            UserManager.loadUsers();
        });

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(UserManager::saveUsers, 1, 1, TimeUnit.MINUTES);

        ServerLifecycleEvents.SERVER_STOPPING.register((server) -> {
            scheduler.shutdown();
            UserManager.saveUsers();
        });
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

    public static <T> TaskChain<T> newChain() {
        return getSingleton().taskChainFactory.newChain();
    }
    public static <T> TaskChain<T> newSharedChain(String name) {
        return getSingleton().taskChainFactory.newSharedChain(name);
    }

}
