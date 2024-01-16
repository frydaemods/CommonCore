package dev.frydae.beguild.client.render;

import lombok.Data;
import net.minecraft.util.Identifier;

@Data
public final class Spritesheet {
    private final Identifier texture;
    private final int width;
    private final int height;

    public ScreenTexturePiece getPiece(int pieceWidth, int pieceHeight, int spritesheetX, int spritesheetY) {
        return new ScreenTexturePiece(texture, width, height, pieceWidth, pieceHeight, spritesheetX, spritesheetY);
    }
}
