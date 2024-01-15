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
    ScreenTexturePiece topLeftPiece;
    ScreenTexturePiece topRightPiece;
    ScreenTexturePiece bottomLeftPiece;
    ScreenTexturePiece bottomRightPiece;
    ScreenTexturePiece topPiece;
    ScreenTexturePiece bottomPiece;
    ScreenTexturePiece leftPiece;
    ScreenTexturePiece rightPiece;

    public UIBaseScreen(T handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);

        setupPieces();
    }

    private void setupPieces() {
        fillerPiece = new ScreenTexturePiece(loadUIPiece("filler"), 7, 7);
        topLeftPiece = new ScreenTexturePiece(loadUIPiece("top_left"), 7, 7);
        topRightPiece = new ScreenTexturePiece(loadUIPiece("top_right"), 7, 7);
        bottomLeftPiece = new ScreenTexturePiece(loadUIPiece("bottom_left"), 7, 7);
        bottomRightPiece = new ScreenTexturePiece(loadUIPiece("bottom_right"), 7, 7);
        topPiece = new ScreenTexturePiece(loadUIPiece("top"), 7, 7);
        bottomPiece = new ScreenTexturePiece(loadUIPiece("bottom"), 7, 7);
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
        topLeftPiece.withInitial(context, x, y)
                .drawTexture();

        topRightPiece.withInitial(context, x, y)
                .xOffset(width - topRightPiece.getWidth())
                .drawTexture();

        bottomLeftPiece.withInitial(context, x, y)
                .yOffset(height - bottomLeftPiece.getHeight())
                .drawTexture();

        bottomRightPiece.withInitial(context, x, y)
                .xOffset(width - bottomRightPiece.getWidth())
                .yOffset(height - bottomRightPiece.getHeight())
                .drawTexture();
        // endregion

        // region Draw Edges
        topPiece.withInitial(context, x, y)
                .xOffset(topLeftPiece.getWidth())
                .setWidth(width - topLeftPiece.getWidth() - topRightPiece.getWidth())
                .drawTexture();

        bottomPiece.withInitial(context, x, y)
                .xOffset(bottomLeftPiece.getWidth())
                .yOffset(height - bottomPiece.getHeight())
                .setWidth(width - bottomLeftPiece.getWidth() - bottomRightPiece.getWidth())
                .drawTexture();

        leftPiece.withInitial(context, x, y)
                .yOffset(topLeftPiece.getHeight())
                .setHeight(height - topLeftPiece.getHeight() - bottomLeftPiece.getHeight())
                .drawTexture();

        rightPiece.withInitial(context, x, y)
                .xOffset(width - rightPiece.getWidth())
                .yOffset(topRightPiece.getHeight())
                .setHeight(height - topRightPiece.getHeight() - bottomRightPiece.getHeight())
                .drawTexture();
        // endregion

        // region Draw Filler
        fillerPiece.withInitial(context, x, y)
                .xOffset(leftPiece.getWidth())
                .yOffset(topPiece.getHeight())
                .setWidth(width - leftPiece.getWidth() - rightPiece.getWidth())
                .setHeight(height - topPiece.getHeight() - bottomPiece.getHeight())
                .drawTexture();
        // endregion
    }
}
