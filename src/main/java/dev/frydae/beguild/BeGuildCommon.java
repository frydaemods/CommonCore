package dev.frydae.beguild;

import dev.frydae.beguild.screens.BeGuildScreenHandlerPayload;
import dev.frydae.beguild.screens.BeGuildScreenHandlerType;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public class BeGuildCommon implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {
        PayloadTypeRegistry.playS2C().register(BeGuildScreenHandlerPayload.ID, BeGuildScreenHandlerPayload.CODEC);

        BeGuildScreenHandlerType.dummy();
    }
}
