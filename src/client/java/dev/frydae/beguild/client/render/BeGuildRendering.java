package dev.frydae.beguild.client.render;

import com.google.common.collect.Maps;
import dev.frydae.beguild.client.mixins.texture.SpriteAtlasHolderAccessor;
import dev.frydae.beguild.client.mixins.texture.SpriteAtlasTextureAccessor;
import dev.frydae.beguild.client.mixins.texture.TextureManagerAccessor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
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

        return Identifier.of("beguild", "textures/gui/" + path + "/" + colorMode + "_" + piece + ".png");
    }

    private static boolean verifySpriteExists(Identifier id) {
        if (!SPRITE_EXISTENCE_CACHE.containsKey(id)) {
            boolean found = ((SpriteAtlasTextureAccessor) ((SpriteAtlasHolderAccessor) MinecraftClient.getInstance().getGuiAtlasManager()).getAtlas()).getSprites().containsKey(id);

            SPRITE_EXISTENCE_CACHE.put(id, found);
        }

        return SPRITE_EXISTENCE_CACHE.get(id);
    }

    private static boolean verifyTextureExists(Identifier id) {
        if (!TEXTURE_EXISTENCE_CACHE.containsKey(id)) {
            boolean found = ((TextureManagerAccessor) MinecraftClient.getInstance().getTextureManager()).getTextures().containsKey(id);

            TEXTURE_EXISTENCE_CACHE.put(id, found);
        }

        return TEXTURE_EXISTENCE_CACHE.get(id);
    }

    private static String alterDarkModePath(String path) {
        return path.substring(0, path.lastIndexOf("/")) + "/dark" + path.substring(path.lastIndexOf("/"));
    }

    public static Identifier processUITexture(Identifier original) {
        return Identifier.of(processUITexture(original.getNamespace() + ":" + original.getPath()));
    }

    public static String processUITexture(String original) {
        if (isDarkMode()) {
            String darkIdentifier = "beguild:" + alterDarkModePath(original);

            // replace old vanilla identifier namespace
            darkIdentifier = darkIdentifier.replace("minecraft:", "");

            if (verifyTextureExists(Identifier.of(darkIdentifier))) {
                return darkIdentifier;
            }
        }

        return original;
    }

    public static Identifier processSpriteTexture(Identifier original) {
        if (isDarkMode()) {
            Identifier darkSprite = Identifier.of(alterDarkModePath(original.getPath()));

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
