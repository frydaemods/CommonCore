package dev.frydae.beguild.client;

import dev.frydae.beguild.client.screens.BeGuildContainerScreen;
import dev.frydae.beguild.screens.BeGuildScreenHandlerType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

@Environment(EnvType.CLIENT)
public class BeGuildClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_SCREEN_HANDLER, BeGuildContainerScreen::new);

        BeGuildRegistry.dummy();

        System.out.println("I turned myself into a client mod Morty");
        System.out.println("I'M CLIENT MOD RICK!!!!");
    }
}
