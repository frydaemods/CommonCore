package dev.frydae.beguild.client.render;

import net.minecraft.util.Identifier;

public record Spritesheet(Identifier texture, int width, int height) {
    public ScreenTexturePiece getPiece(int pieceWidth, int pieceHeight, int spritesheetX, int spritesheetY) {
        return new ScreenTexturePiece(texture, width, height, pieceWidth, pieceHeight, spritesheetX, spritesheetY);
    }
}
