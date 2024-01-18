package dev.frydae.beguild.client.screens;

import dev.frydae.beguild.client.render.BeGuildRendering;
import dev.frydae.beguild.client.render.ScreenTexturePiece;
import dev.frydae.beguild.client.render.Spritesheet;
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
        Spritesheet spritesheet = new Spritesheet(loadContainerPiece("container"), 48, 48);

        topLeftPiece = spritesheet.getPiece(16, 16, 0, 0);
        topPiece = spritesheet.getPiece(16, 16, 16, 0);
        topRightPiece = spritesheet.getPiece(16, 16, 32, 0);
        leftPiece = spritesheet.getPiece(16, 16, 0, 16);
        fillerPiece = spritesheet.getPiece(16, 16, 16, 16);
        rightPiece = spritesheet.getPiece(16, 16, 32, 16);
        bottomLeftPiece = spritesheet.getPiece(16, 16, 0, 32);
        bottomPiece = spritesheet.getPiece(16, 16, 16, 32);
        bottomRightPiece = spritesheet.getPiece(16, 16, 32, 32);

        loadUIParts();
    }

    protected void loadUIParts() {}

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(context, mouseX, mouseY);
    }

    protected Identifier loadContainerPiece(String piece) {
        return BeGuildRendering.loadUIPiece("container", piece);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        setupPieces();

        // region Draw Corners
        topLeftPiece.withInitial(context, x, y)
                .drawTexture();

        topRightPiece.withInitial(context, x, y)
                .xOffset(backgroundWidth - topRightPiece.getWidth())
                .drawTexture();

        bottomLeftPiece.withInitial(context, x, y)
                .yOffset(backgroundHeight - bottomLeftPiece.getHeight())
                .drawTexture();

        bottomRightPiece.withInitial(context, x, y)
                .xOffset(backgroundWidth - bottomRightPiece.getWidth())
                .yOffset(backgroundHeight - bottomRightPiece.getHeight())
                .drawTexture();
        // endregion

        // region Draw Edges
        topPiece.withInitial(context, x, y)
                .xOffset(topLeftPiece.getWidth())
                .setWidth(backgroundWidth - topLeftPiece.getWidth() - topRightPiece.getWidth())
                .drawTexture();

        bottomPiece.withInitial(context, x, y)
                .xOffset(bottomLeftPiece.getWidth())
                .yOffset(backgroundHeight - bottomPiece.getHeight())
                .setWidth(backgroundWidth - bottomLeftPiece.getWidth() - bottomRightPiece.getWidth())
                .drawTexture();

        leftPiece.withInitial(context, x, y)
                .yOffset(topLeftPiece.getHeight())
                .setHeight(backgroundHeight - topLeftPiece.getHeight() - bottomLeftPiece.getHeight())
                .drawTexture();

        rightPiece.withInitial(context, x, y)
                .xOffset(backgroundWidth - rightPiece.getWidth())
                .yOffset(topRightPiece.getHeight())
                .setHeight(backgroundHeight - topRightPiece.getHeight() - bottomRightPiece.getHeight())
                .drawTexture();
        // endregion

        // region Draw Filler
        fillerPiece.withInitial(context, x, y)
                .xOffset(leftPiece.getWidth())
                .yOffset(topPiece.getHeight())
                .setWidth(backgroundWidth - leftPiece.getWidth() - rightPiece.getWidth())
                .setHeight(backgroundHeight - topPiece.getHeight() - bottomPiece.getHeight())
                .drawTexture();
        // endregion
    }
}
