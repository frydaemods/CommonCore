package dev.frydae.commoncore.commands;

import dev.frydae.commands.FabricCommandManager;

public final class Commands {
    public static void registerCommands() {
        new MiscCommands();
        new ModeCommand();

        FabricCommandManager.upsertCommands();
    }
}
