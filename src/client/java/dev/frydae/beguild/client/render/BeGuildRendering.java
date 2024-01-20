package dev.frydae.beguild.client.render;

import com.google.common.collect.Maps;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.Sprite;
import net.minecraft.util.Identifier;

import java.util.Map;

@Environment(EnvType.CLIENT)
public final class BeGuildRendering {
    private static final Map<Identifier, Boolean> TEXTURE_EXISTENCE_CACHE = Maps.newConcurrentMap();
    private static final Map<Identifier, Boolean> SPRITE_EXISTENCE_CACHE = Maps.newConcurrentMap();

    private BeGuildRendering() {}

    public static boolean isDarkMode() {
        // TODO: Add config option
        return true;
    }

    public static Identifier loadUIPiece(String path, String piece) {
        String colorMode = BeGuildRendering.isDarkMode() ? "dark" : "light";

        return new Identifier("beguild", "textures/gui/" + path + "/" + colorMode + "_" + piece + ".png");
    }

    private static boolean verifySpriteExists(Identifier id) {
        if (!SPRITE_EXISTENCE_CACHE.containsKey(id)) {
            Sprite sprite = MinecraftClient.getInstance().getGuiAtlasManager().getSprite(id);

            SPRITE_EXISTENCE_CACHE.put(id, sprite != null);
        }

        return SPRITE_EXISTENCE_CACHE.get(id);
    }

    private static boolean verifyTextureExists(Identifier id) {
        if (!TEXTURE_EXISTENCE_CACHE.containsKey(id)) {
            AbstractTexture texture = MinecraftClient.getInstance().getTextureManager().getTexture(id);

            TEXTURE_EXISTENCE_CACHE.put(id, texture != null);
        }

        return TEXTURE_EXISTENCE_CACHE.get(id);
    }

    private static String alterDarkModePath(String path) {
        return path.substring(0, path.lastIndexOf("/")) + "/dark" + path.substring(path.lastIndexOf("/"));
    }

    public static Identifier processUITexture(Identifier original) {
        return new Identifier(processUITexture(original.getNamespace() + ":" + original.getPath()));
    }

    public static String processUITexture(String original) {
        if (isDarkMode()) {
            String darkIdentifier = "beguild:" + alterDarkModePath(original);

            // replace old vanilla identifier namespace
            darkIdentifier = darkIdentifier.replace("minecraft:", "");

            if (verifyTextureExists(new Identifier(darkIdentifier))) {
                return darkIdentifier;
            }
        }

        return original;
    }

    public static Identifier processSpriteTexture(Identifier original) {
        if (isDarkMode()) {
            Identifier darkSprite = new Identifier(alterDarkModePath(original.getPath()));

            if (verifySpriteExists(darkSprite)) {
                return darkSprite;
            }
        }

        return original;
    }

    public static int getHandledScreenTextColor() {
        if (isDarkMode()) {
            return 0xaaaaaa;
        }

        return 0x404040;
    }
}
