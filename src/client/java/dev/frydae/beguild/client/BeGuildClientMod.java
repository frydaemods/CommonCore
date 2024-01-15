package dev.frydae.beguild.client;

import dev.frydae.beguild.screens.BeGuildContainerScreenHandler;
import dev.frydae.beguild.screens.BeGuildScreenHandlerType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

@Environment(EnvType.CLIENT)
public class BeGuildClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BeGuildScreenHandlerType.registerHandlerTypes();

        for (ExtendedScreenHandlerType<BeGuildContainerScreenHandler> value : BeGuildScreenHandlerType.getHandlers().values()) {
            HandledScreens.register(value, BeGuildContainerScreen::new);
        }

        System.out.println("I turned myself into a client mod Morty");
        System.out.println("I'M CLIENT MOD RICK!!!!");
    }
}
