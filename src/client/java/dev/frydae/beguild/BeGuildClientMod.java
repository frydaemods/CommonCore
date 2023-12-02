package dev.frydae.beguild;

import net.fabricmc.api.ClientModInitializer;

public class BeGuildClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        System.out.println("I turned myself into a client mod Morty");
        System.out.println("I'M CLIENT MOD RICK!!!!");
    }
}
