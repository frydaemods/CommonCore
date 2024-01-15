package dev.frydae.beguild.client.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.Sprite;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public final class BeGuildRendering {
    private BeGuildRendering() {}

    public static boolean isDarkMode() {
        // TODO: Add config option
        return true;
    }

    private static boolean verifySpriteExists(Identifier id) {
        Sprite sprite = MinecraftClient.getInstance().getGuiAtlasManager().getSprite(id);

        return sprite != null;
    }

    public static Identifier processUITexture(Identifier original) {
        if (isDarkMode()) {
            String path = original.getPath();

            return new Identifier("beguild", path.substring(0, path.lastIndexOf("/")) + "/dark" + path.substring(path.lastIndexOf("/")));
        }

        return original;
    }

    public static Identifier processSpriteTexture(Identifier original) {
        if (isDarkMode()) {
            String path = original.getPath();

            Identifier darkSprite = new Identifier(path.substring(0, path.lastIndexOf("/")) + "/dark" + path.substring(path.lastIndexOf("/")));

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
