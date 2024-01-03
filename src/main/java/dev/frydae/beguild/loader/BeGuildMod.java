package dev.frydae.beguild.loader;

import dev.frydae.beguild.events.ServerLifecycleEvents;
import dev.frydae.commands.FabricCommandCompletions;
import dev.frydae.commands.FabricCommandConditions;
import dev.frydae.commands.FabricCommandContexts;
import dev.frydae.commands.FabricCommandManager;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.minecraft.server.MinecraftServer;

public class BeGuildMod implements DedicatedServerModInitializer {
    @Override
    public final void onInitializeServer() {
        this.onInitialize();

        this.registerCommands(FabricCommandManager.getSingleton().getCommandCompletions(), FabricCommandManager.getSingleton().getCommandContexts(), FabricCommandManager.getSingleton().getCommandConditions());
        FabricCommandManager.registerCommands();

        ServerLifecycleEvents.PRE_SERVER_STARTING.register(this::preStarting);
        ServerLifecycleEvents.SERVER_STARTING.register(this::onStarting);
        ServerLifecycleEvents.POST_SERVER_STARTING.register(this::postStarting);

        ServerLifecycleEvents.PRE_SERVER_STARTED.register(this::preStarted);
        ServerLifecycleEvents.SERVER_STARTED.register(this::onStarted);
        ServerLifecycleEvents.POST_SERVER_STARTED.register(this::postStarted);

        ServerLifecycleEvents.PRE_SERVER_STOPPING.register(this::preStopping);
        ServerLifecycleEvents.SERVER_STOPPING.register(this::onStopping);
        ServerLifecycleEvents.POST_SERVER_STOPPING.register(this::postStopping);

        ServerLifecycleEvents.PRE_SERVER_STOPPED.register(this::preStopped);
        ServerLifecycleEvents.SERVER_STOPPED.register(this::onStopped);
        ServerLifecycleEvents.POST_SERVER_STOPPED.register(this::postStopped);
    }

    public void onInitialize() {}
    public void registerCommands(FabricCommandCompletions commandCompletions, FabricCommandContexts commandContexts, FabricCommandConditions commandConditions) {}

    public void preStarting(MinecraftServer server) {}
    public void onStarting(MinecraftServer server) {}
    public void postStarting(MinecraftServer server) {}

    public void preStarted(MinecraftServer server) {}
    public void onStarted(MinecraftServer server) {}
    public void postStarted(MinecraftServer server) {}

    public void preStopping(MinecraftServer server) {}
    public void onStopping(MinecraftServer server) {}
    public void postStopping(MinecraftServer server) {}

    public void preStopped(MinecraftServer server) {}
    public void onStopped(MinecraftServer server) {}
    public void postStopped(MinecraftServer server) {}
}
