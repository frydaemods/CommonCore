package dev.frydae.beguild;

import dev.frydae.beguild.screens.BeGuildScreenHandlerType;
import net.fabricmc.api.DedicatedServerModInitializer;

public class BeGuildCommon implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {
        BeGuildScreenHandlerType.dummy();
    }
}
