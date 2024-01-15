package dev.frydae.beguild.client.screens;

import dev.frydae.beguild.client.render.BeGuildRendering;
import dev.frydae.beguild.client.render.ScreenTexturePiece;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public abstract class UIBaseScreen<T extends ScreenHandler> extends HandledScreen<T> implements ScreenHandlerProvider<T> {
    ScreenTexturePiece fillerPiece;
    ScreenTexturePiece upperLeftPiece;
    ScreenTexturePiece upperRightPiece;
    ScreenTexturePiece lowerLeftPiece;
    ScreenTexturePiece lowerRightPiece;
    ScreenTexturePiece upperPiece;
    ScreenTexturePiece lowerPiece;
    ScreenTexturePiece leftPiece;
    ScreenTexturePiece rightPiece;

    public UIBaseScreen(T handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);

        setupPieces();
    }

    private void setupPieces() {
        fillerPiece = new ScreenTexturePiece(loadUIPiece("filler"), 7, 7);
        upperLeftPiece = new ScreenTexturePiece(loadUIPiece("upper_corner_left"), 7, 7);
        upperRightPiece = new ScreenTexturePiece(loadUIPiece("upper_corner_right"), 7, 7);
        lowerLeftPiece = new ScreenTexturePiece(loadUIPiece("lower_corner_left"), 7, 7);
        lowerRightPiece = new ScreenTexturePiece(loadUIPiece("lower_corner_right"), 7, 7);
        upperPiece = new ScreenTexturePiece(loadUIPiece("upper"), 7, 7);
        lowerPiece = new ScreenTexturePiece(loadUIPiece("lower"), 7, 7);
        leftPiece = new ScreenTexturePiece(loadUIPiece("left"), 7, 7);
        rightPiece = new ScreenTexturePiece(loadUIPiece("right"), 7, 7);

        loadUIParts();
    }

    protected void loadUIParts() {}

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(context, mouseX, mouseY);
    }

    protected Identifier loadUIPiece(String piece) {
        String colorMode = BeGuildRendering.isDarkMode() ? "dark" : "light";

        return new Identifier("beguild", "textures/gui/container/" + colorMode + "_" + piece + ".png");
    }

    protected void drawBase(DrawContext context, int width, int height) {
        // region Draw Corners
        upperLeftPiece.withInitial(context, x, y)
                .drawTexture();

        upperRightPiece.withInitial(context, x, y)
                .xOffset(width - upperRightPiece.getWidth())
                .drawTexture();

        lowerLeftPiece.withInitial(context, x, y)
                .yOffset(height - lowerLeftPiece.getHeight())
                .drawTexture();

        lowerRightPiece.withInitial(context, x, y)
                .xOffset(width - lowerRightPiece.getWidth())
                .yOffset(height - lowerRightPiece.getHeight())
                .drawTexture();
        // endregion

        // region Draw Edges
        upperPiece.withInitial(context, x, y)
                .xOffset(upperLeftPiece.getWidth())
                .setWidth(width - upperLeftPiece.getWidth() - upperRightPiece.getWidth())
                .drawTexture();

        lowerPiece.withInitial(context, x, y)
                .xOffset(lowerLeftPiece.getWidth())
                .yOffset(height - lowerPiece.getHeight())
                .setWidth(width - lowerLeftPiece.getWidth() - lowerRightPiece.getWidth())
                .drawTexture();

        leftPiece.withInitial(context, x, y)
                .yOffset(upperLeftPiece.getHeight())
                .setHeight(height - upperLeftPiece.getHeight() - lowerLeftPiece.getHeight())
                .drawTexture();

        rightPiece.withInitial(context, x, y)
                .xOffset(width - rightPiece.getWidth())
                .yOffset(upperRightPiece.getHeight())
                .setHeight(height - upperRightPiece.getHeight() - lowerRightPiece.getHeight())
                .drawTexture();
        // endregion

        // region Draw Filler
        fillerPiece.withInitial(context, x, y)
                .xOffset(leftPiece.getWidth())
                .yOffset(upperPiece.getHeight())
                .setWidth(width - leftPiece.getWidth() - rightPiece.getWidth())
                .setHeight(height - upperPiece.getHeight() - lowerPiece.getHeight())
                .drawTexture();
        // endregion
    }
}
