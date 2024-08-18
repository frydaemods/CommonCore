package dev.frydae.beguild.screens;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BeGuildScreenHandlerType {
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler, BeGuildScreenHandlerPayload> BEGUILD_SCREEN_HANDLER = register();

    private static ExtendedScreenHandlerType<BeGuildContainerScreenHandler, BeGuildScreenHandlerPayload> register() {
        ExtendedScreenHandlerType<BeGuildContainerScreenHandler, BeGuildScreenHandlerPayload> handler =
                new ExtendedScreenHandlerType<>(BeGuildContainerScreenHandler::create, BeGuildScreenHandlerPayload.CODEC);

        return Registry.register(Registries.SCREEN_HANDLER, Identifier.of("beguild", "container"), handler);
    }

    public static void dummy() {
        // only to load this class
    }
}
