package dev.frydae.beguild.client.screens;

import dev.frydae.beguild.client.render.ScreenTexturePiece;
import dev.frydae.beguild.screens.BeGuildContainerScreenHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;

import static dev.frydae.beguild.data.Constants.INVENTORY_SLOT_OFFSET;
import static dev.frydae.beguild.data.Constants.INVENTORY_TITLE_OFFSET;
import static dev.frydae.beguild.data.Constants.PLAYER_INVENTORY_COLUMNS;
import static dev.frydae.beguild.data.Constants.PLAYER_INVENTORY_CONTAINER_SEPARATOR_PIXELS;
import static dev.frydae.beguild.data.Constants.PLAYER_INVENTORY_HOTBAR_SEPARATOR_PIXELS;
import static dev.frydae.beguild.data.Constants.PLAYER_INVENTORY_SLOT_OFFSET;
import static dev.frydae.beguild.data.Constants.PLAYER_INVENTORY_TITLE_OFFSET;

@Environment(EnvType.CLIENT)
public final class BeGuildContainerScreen extends UIBaseScreen<BeGuildContainerScreenHandler> {
    private static ScreenTexturePiece slotPiece;

    public BeGuildContainerScreen(BeGuildContainerScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);

        int rows = handler.getRows();
        int columns = handler.getColumns();

        this.backgroundWidth = INVENTORY_SLOT_OFFSET + (columns * slotPiece.getTextureWidth()) + INVENTORY_SLOT_OFFSET;
        this.backgroundHeight = INVENTORY_SLOT_OFFSET +
                INVENTORY_TITLE_OFFSET +
                (rows * slotPiece.getTextureHeight()) +
                PLAYER_INVENTORY_CONTAINER_SEPARATOR_PIXELS +
                (3 * slotPiece.getTextureHeight()) +
                PLAYER_INVENTORY_HOTBAR_SEPARATOR_PIXELS +
                slotPiece.getTextureHeight() +
                INVENTORY_SLOT_OFFSET;

        this.playerInventoryTitleY = INVENTORY_SLOT_OFFSET + INVENTORY_TITLE_OFFSET + (rows * slotPiece.getTextureHeight()) + PLAYER_INVENTORY_TITLE_OFFSET;
        this.playerInventoryTitleX = INVENTORY_SLOT_OFFSET + ((columns - PLAYER_INVENTORY_COLUMNS) * PLAYER_INVENTORY_SLOT_OFFSET);
    }

    @Override
    protected void loadUIParts() {
        slotPiece = new ScreenTexturePiece(loadUIPiece("slot"), 18, 18);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        super.drawBackground(context, delta, mouseX, mouseY);

        for (Slot slot : handler.slots) {
            slotPiece.withInitial(context, x, y)
                    .xOffset(slot.x - 1)
                    .yOffset(slot.y - 1)
                    .drawTexture();
        }
    }
}
