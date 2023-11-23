package dev.frydae.commoncore;

import dev.frydae.commoncore.commands.Commands;
import net.fabricmc.api.DedicatedServerModInitializer;

public class CommonCore implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {
        Commands.registerCommands();
    }
}
