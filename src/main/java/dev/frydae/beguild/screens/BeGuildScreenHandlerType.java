package dev.frydae.beguild.screens;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BeGuildScreenHandlerType {
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_SCREEN_HANDLER = register();

    private static ExtendedScreenHandlerType<BeGuildContainerScreenHandler> register() {
        ExtendedScreenHandlerType<BeGuildContainerScreenHandler> handler = new ExtendedScreenHandlerType<>(BeGuildContainerScreenHandler::create);

        return Registry.register(Registries.SCREEN_HANDLER, new Identifier("beguild", "container"), handler);
    }

    public static void dummy() {
        // only to load this class
    }
}
