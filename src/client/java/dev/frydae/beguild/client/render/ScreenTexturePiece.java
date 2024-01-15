package dev.frydae.beguild.client.render;

import lombok.Getter;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;

public final class ScreenTexturePiece {
    @Getter private final Identifier texture;
    @Getter private final int width;
    @Getter private final int height;

    private DrawContext context;
    private int startX = 0;
    private int startY = 0;
    private int xOffset = 0;
    private int yOffset = 0;

    private int drawWidth;
    private int drawHeight;

    public ScreenTexturePiece(Identifier texture, int width, int height) {
        this.texture = texture;
        this.width = width;
        this.height = height;

        this.drawWidth = width;
        this.drawHeight = height;
    }

    public ScreenTexturePiece withInitial(DrawContext context, int x, int y) {
        ScreenTexturePiece clone = new ScreenTexturePiece(texture, width, height);

        clone.context = context;
        clone.startX = x;
        clone.startY = y;

        return clone;
    }

    public ScreenTexturePiece xOffset(int x) {
        this.xOffset += x;

        return this;
    }

    public ScreenTexturePiece yOffset(int y) {
        this.yOffset += y;

        return this;
    }

    public ScreenTexturePiece repeatWidth(int times) {
        this.drawWidth = width * times;

        return this;
    }

    public ScreenTexturePiece addWidth(int width) {
        this.drawWidth += width;

        return this;
    }

    public ScreenTexturePiece setWidth(int width) {
        this.drawWidth = width;

        return this;
    }

    public ScreenTexturePiece repeatHeight(int times) {
        this.drawHeight = height * times;

        return this;
    }

    public ScreenTexturePiece addHeight(int height) {
        this.drawHeight += height;

        return this;
    }

    public ScreenTexturePiece setHeight(int height) {
        this.drawHeight = height;

        return this;
    }

    public void drawTexture() {
        context.drawTexture(texture, startX + xOffset, startY + yOffset, 0, 0, drawWidth, drawHeight, width, height);
    }
}
