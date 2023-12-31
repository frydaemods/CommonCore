package dev.frydae.beguild.loader;

import dev.frydae.commands.FabricCommandCompletions;
import dev.frydae.commands.FabricCommandConditions;
import dev.frydae.commands.FabricCommandContexts;
import dev.frydae.commands.FabricCommandManager;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;

public class BeGuildMod implements DedicatedServerModInitializer {
    @Override
    public final void onInitializeServer() {
        this.onInitialize();

        this.registerCommands(FabricCommandManager.getSingleton().getCommandCompletions(), FabricCommandManager.getSingleton().getCommandContexts(), FabricCommandManager.getSingleton().getCommandConditions());
        FabricCommandManager.registerCommands();

        ServerLifecycleEvents.SERVER_STARTING.register(this::onStarting);
        ServerLifecycleEvents.SERVER_STARTED.register(this::onStarted);
        ServerLifecycleEvents.SERVER_STOPPING.register(this::onStopping);
        ServerLifecycleEvents.SERVER_STOPPED.register(this::onStopped);
    }

    public void onInitialize() {}
    public void registerCommands(FabricCommandCompletions commandCompletions, FabricCommandContexts commandContexts, FabricCommandConditions commandConditions) {}
    public void onStarting(MinecraftServer server) {}
    public void onStarted(MinecraftServer server) {}
    public void onStopping(MinecraftServer server) {}
    public void onStopped(MinecraftServer server) {}
}
