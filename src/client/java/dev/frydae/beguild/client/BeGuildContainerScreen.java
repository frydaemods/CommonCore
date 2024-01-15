package dev.frydae.beguild.client;

import dev.frydae.beguild.client.render.BeGuildRendering;
import dev.frydae.beguild.client.render.ScreenTexturePiece;
import dev.frydae.beguild.screens.BeGuildContainerScreenHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static dev.frydae.beguild.data.Constants.INVENTORY_SLOT_PIXEL_SIZE;
import static dev.frydae.beguild.data.Constants.INVENTORY_TITLE_HEIGHT_PIXELS;
import static dev.frydae.beguild.data.Constants.INVENTORY_VERTICAL_BORDER_PIXELS;
import static dev.frydae.beguild.data.Constants.PLAYER_INVENTORY_COLUMNS;
import static dev.frydae.beguild.data.Constants.PLAYER_INVENTORY_HEIGHT;
import static dev.frydae.beguild.data.Constants.PLAYER_INVENTORY_HOTBAR_SEPARATOR_PIXELS;
import static dev.frydae.beguild.data.Constants.PLAYER_INVENTORY_SLOT_OFFSET;

@Environment(EnvType.CLIENT)
public final class BeGuildContainerScreen extends HandledScreen<BeGuildContainerScreenHandler> implements ScreenHandlerProvider<BeGuildContainerScreenHandler> {
    private final int rows;
    private final int columns;

    private static final ScreenTexturePiece[] UI_PIECES = new ScreenTexturePiece[10];

    private static ScreenTexturePiece slotPiece;
    private static ScreenTexturePiece fillerPiece;
    private static ScreenTexturePiece upperLeftPiece;
    private static ScreenTexturePiece upperRightPiece;
    private static ScreenTexturePiece lowerLeftPiece;
    private static ScreenTexturePiece lowerRightPiece;
    private static ScreenTexturePiece upperPiece;
    private static ScreenTexturePiece lowerPiece;
    private static ScreenTexturePiece leftPiece;
    private static ScreenTexturePiece rightPiece;

    public BeGuildContainerScreen(BeGuildContainerScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);

        this.rows = handler.getRows();
        this.columns = handler.getColumns();

        this.backgroundHeight = INVENTORY_TITLE_HEIGHT_PIXELS + PLAYER_INVENTORY_HEIGHT + (rows * INVENTORY_SLOT_PIXEL_SIZE);
        this.backgroundWidth = INVENTORY_VERTICAL_BORDER_PIXELS + (columns * INVENTORY_SLOT_PIXEL_SIZE) + INVENTORY_VERTICAL_BORDER_PIXELS;

        setupPieces();

        this.playerInventoryTitleY = upperPiece.getHeight() + (rows * slotPiece.getHeight()) + 3;
        this.playerInventoryTitleX = leftPiece.getWidth() + ((columns - PLAYER_INVENTORY_COLUMNS) * PLAYER_INVENTORY_SLOT_OFFSET);
    }

    private Identifier loadUIPiece(String piece) {
        String colorMode = BeGuildRendering.isDarkMode() ? "dark" : "light";

        return new Identifier("beguild", "textures/gui/container/" + colorMode + "_" + piece + ".png");
    }

    private void setupPieces() {
        slotPiece = new ScreenTexturePiece(loadUIPiece("slot"), 18, 18);
        fillerPiece = new ScreenTexturePiece(loadUIPiece("filler"), 18, 14);
        upperLeftPiece = new ScreenTexturePiece(loadUIPiece("upper_corner_left"), 7, 17);
        upperRightPiece = new ScreenTexturePiece(loadUIPiece("upper_corner_right"), 7, 17);
        lowerLeftPiece = new ScreenTexturePiece(loadUIPiece("lower_corner_left"), 7, 7);
        lowerRightPiece = new ScreenTexturePiece(loadUIPiece("lower_corner_right"), 7, 7);
        upperPiece = new ScreenTexturePiece(loadUIPiece("upper"), 18, 17);
        lowerPiece = new ScreenTexturePiece(loadUIPiece("lower"), 18, 7);
        leftPiece = new ScreenTexturePiece(loadUIPiece("left"), 7, 18);
        rightPiece = new ScreenTexturePiece(loadUIPiece("right"), 7, 18);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
//        setupPieces();

        int totalWidth = leftPiece.getWidth() + (columns * slotPiece.getWidth()) + rightPiece.getWidth();
        int totalHeight = upperPiece.getHeight() + (rows * slotPiece.getHeight()) + fillerPiece.getHeight() + (3 * slotPiece.getHeight()) + PLAYER_INVENTORY_HOTBAR_SEPARATOR_PIXELS + slotPiece.getHeight() + lowerPiece.getHeight();

        final int x = (width - totalWidth) / 2;
        final int y = (height - totalHeight) / 2;

        int internalXOffset = upperLeftPiece.getWidth();
        int internalWidth = columns * slotPiece.getWidth();
        int inventoryWidth = PLAYER_INVENTORY_COLUMNS * slotPiece.getWidth();

        int containerSlotYOffset = UI_PIECES[6].getHeight();
        int middleFillerYOffset = containerSlotYOffset + (rows * slotPiece.getHeight());
        int inventorySlotYOffset = middleFillerYOffset + fillerPiece.getHeight();
        int hotbarSeparatorYOffset = inventorySlotYOffset + (3 * slotPiece.getHeight());
        int hotbarYOffset = hotbarSeparatorYOffset + PLAYER_INVENTORY_HOTBAR_SEPARATOR_PIXELS;
        int bottomYOffset = hotbarYOffset + slotPiece.getHeight();

        int inventorySlotOffset = (columns - PLAYER_INVENTORY_COLUMNS) * PLAYER_INVENTORY_SLOT_OFFSET;

        // Draw top
        upperLeftPiece.withInitial(context, x, y)
                .drawTexture();

        upperPiece.withInitial(context, x, y)
                .xOffset(upperLeftPiece.getWidth())
                .repeatWidth(columns)
                .drawTexture();

        upperRightPiece.withInitial(context, x, y)
                .xOffset(upperLeftPiece.getWidth())
                .xOffset(columns * slotPiece.getWidth())
                .drawTexture();

        // Draw bottom left
        lowerLeftPiece.withInitial(context, x, y)
                .yOffset(bottomYOffset)
                .drawTexture();

        // Draw bottom middle
        lowerPiece.withInitial(context, x, y)
                .xOffset(lowerLeftPiece.getWidth())
                .yOffset(bottomYOffset)
                .repeatWidth(columns)
                .drawTexture();

        // Draw bottom right
        lowerRightPiece.withInitial(context, x, y)
                .xOffset(lowerLeftPiece.getWidth())
                .xOffset(internalWidth)
                .yOffset(bottomYOffset)
                .drawTexture();

        // Draw Container Slots
        slotPiece.withInitial(context, x, y)
                .xOffset(internalXOffset)
                .yOffset(containerSlotYOffset)
                .repeatWidth(columns)
                .repeatHeight(rows)
                .drawTexture();

        // Draw Inventory Slots
        slotPiece.withInitial(context, x, y)
                .xOffset(internalXOffset + inventorySlotOffset)
                .yOffset(inventorySlotYOffset)
                .repeatWidth(9)
                .repeatHeight(3)
                .drawTexture();

        // Draw Hotbar Slots
        slotPiece.withInitial(context, x, y)
                .xOffset(internalXOffset + inventorySlotOffset)
                .yOffset(hotbarYOffset)
                .repeatWidth(9)
                .drawTexture();

        // Draw Left Side
        leftPiece.withInitial(context, x, y)
                .yOffset(containerSlotYOffset)
                .repeatHeight(rows + 4)
                .addHeight(fillerPiece.getHeight())
                .addHeight(PLAYER_INVENTORY_HOTBAR_SEPARATOR_PIXELS)
                .drawTexture();

        // Draw Right Side
        rightPiece.withInitial(context, x, y)
                .xOffset(internalXOffset + internalWidth)
                .yOffset(containerSlotYOffset)
                .repeatHeight(rows + 4)
                .addHeight(fillerPiece.getHeight())
                .addHeight(PLAYER_INVENTORY_HOTBAR_SEPARATOR_PIXELS)
                .drawTexture();

        // Draw Middle Filler
        fillerPiece.withInitial(context, x, y)
                .xOffset(internalXOffset)
                .yOffset(middleFillerYOffset)
                .repeatWidth(columns)
                .drawTexture();

        // Draw Hotbar Separator
        fillerPiece.withInitial(context, x, y)
                .xOffset(internalXOffset + inventorySlotOffset)
                .yOffset(hotbarSeparatorYOffset)
                .repeatWidth(9)
                .setHeight(PLAYER_INVENTORY_HOTBAR_SEPARATOR_PIXELS)
                .drawTexture();

        // Draw Left Inventory Side Filler
        fillerPiece.withInitial(context, x, y)
                .xOffset(internalXOffset)
                .yOffset(inventorySlotYOffset)
                .setWidth(inventorySlotOffset)
                .setHeight(bottomYOffset - inventorySlotYOffset)
                .drawTexture();

        // Draw Right Inventory Side Filler
        fillerPiece.withInitial(context, x, y)
                .xOffset(internalXOffset + inventorySlotOffset + inventoryWidth)
                .yOffset(inventorySlotYOffset)
                .setWidth(inventorySlotOffset)
                .setHeight(bottomYOffset - inventorySlotYOffset)
                .drawTexture();
    }
}
