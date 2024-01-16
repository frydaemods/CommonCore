package dev.frydae.beguild.client.render;

import lombok.Getter;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;

public final class ScreenTexturePiece {
    @Getter private final Identifier texture;
    @Getter private final int textureWidth;
    @Getter private final int textureHeight;

    private DrawContext context;
    private int startX = 0;
    private int startY = 0;
    private int xOffset = 0;
    private int yOffset = 0;

    private final int u;
    private final int v;

    @Getter private final int regionWidth;
    @Getter private final int regionHeight;

    @Getter private int width;
    @Getter private int height;

    public ScreenTexturePiece(Identifier texture, int width, int height) {
        this(texture, width, height, width, height, 0, 0);
    }

    public ScreenTexturePiece(Identifier texture, int textureWidth, int textureHeight, int regionWidth, int regionHeight, int u, int v) {
        this.texture = texture;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;

        this.regionWidth = regionWidth;
        this.regionHeight = regionHeight;

        this.width = regionWidth;
        this.height = regionHeight;

        this.u = u;
        this.v = v;
    }

    public ScreenTexturePiece withInitial(DrawContext context, int x, int y) {
        ScreenTexturePiece clone = new ScreenTexturePiece(texture, textureWidth, textureHeight, width, height, u, v);

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
        this.width = textureWidth * times;

        return this;
    }

    public ScreenTexturePiece addWidth(int width) {
        this.width += width;

        return this;
    }

    public ScreenTexturePiece setWidth(int width) {
        this.width = width;

        return this;
    }

    public ScreenTexturePiece repeatHeight(int times) {
        this.height = textureHeight * times;

        return this;
    }

    public ScreenTexturePiece addHeight(int height) {
        this.height += height;

        return this;
    }

    public ScreenTexturePiece setHeight(int height) {
        this.height = height;

        return this;
    }

    public void drawTexture() {
        context.drawTexture(texture, startX + xOffset, startY + yOffset, width, height, u, v, regionWidth, regionHeight, textureWidth, textureHeight);
    }
}
