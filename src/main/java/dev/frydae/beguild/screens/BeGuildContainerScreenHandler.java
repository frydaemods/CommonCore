package dev.frydae.beguild.screens;

import lombok.Getter;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import org.apache.commons.lang3.mutable.MutableInt;

import static dev.frydae.beguild.data.Constants.INVENTORY_SLOT_OFFSET;
import static dev.frydae.beguild.data.Constants.INVENTORY_TITLE_OFFSET;
import static dev.frydae.beguild.data.Constants.PLAYER_INVENTORY_CONTAINER_SEPARATOR_PIXELS;
import static dev.frydae.beguild.data.Constants.PLAYER_INVENTORY_HOTBAR_SEPARATOR_PIXELS;

@Getter
public class BeGuildContainerScreenHandler extends ScreenHandler {
    private static final int SLOT_X_OFFSET_PIXELS = 12;
    private static final int SLOT_Y_OFFSET_PIXELS = 18;
    private static final int SLOT_PIXEL_SIZE = 18;

    private static final int PLAYER_INVENTORY_SLOT_OFFSET = 9;
    private static final int PLAYER_INVENTORY_COLUMNS = 9;

    private final Inventory inventory;
    private final int rows;
    private final int columns;
    private final BeGuildScreenHandlerPayload payload;

    public BeGuildContainerScreenHandler(ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, int rows, int columns, BeGuildScreenHandlerPayload payload) {
        this(type, syncId, playerInventory, new SimpleInventory(columns * rows), rows, columns, payload);
    }

    public BeGuildContainerScreenHandler(ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, Inventory inventory, int rows, int columns, BeGuildScreenHandlerPayload payload) {
        super(type, syncId);

        checkSize(inventory, rows * columns);

        this.inventory = inventory;
        this.rows = rows;
        this.columns = columns;
        this.payload = payload;

        inventory.onOpen(playerInventory.player);

        MutableInt xOffset = new MutableInt(0);
        MutableInt yOffset = new MutableInt(0);

        drawContainerSlots(inventory, columns, xOffset, yOffset);
        drawInventorySlots(playerInventory, xOffset, yOffset);
        drawHotbarSlots(playerInventory, xOffset, yOffset);
    }

    private void drawContainerSlots(Inventory inventory, int columns, MutableInt xOffset, MutableInt yOffset) {
        xOffset.add(1 + INVENTORY_SLOT_OFFSET);
        yOffset.add(INVENTORY_SLOT_OFFSET + INVENTORY_TITLE_OFFSET);

        for (int row = 0; row < this.rows; row++) {
            for (int column = 0; column < this.columns; column++) {
                int lesserXOffset = Math.max(PLAYER_INVENTORY_COLUMNS - columns, 0) * PLAYER_INVENTORY_SLOT_OFFSET;

                int index = column + (row * columns);
                int x = lesserXOffset + xOffset.intValue() + (column * SLOT_PIXEL_SIZE);

                int y = yOffset.intValue() + (row * SLOT_PIXEL_SIZE);

                addSlot(new Slot(inventory, index, x, y));
            }
        }
    }

    private void drawInventorySlots(PlayerInventory playerInventory, MutableInt xOffset, MutableInt yOffset) {
        xOffset.add(Math.max(columns - PLAYER_INVENTORY_COLUMNS, 0) * (SLOT_PIXEL_SIZE / 2));
        yOffset.add((rows * SLOT_PIXEL_SIZE) + PLAYER_INVENTORY_CONTAINER_SEPARATOR_PIXELS);

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < PLAYER_INVENTORY_COLUMNS; column++) {
                int index = column + (row * PLAYER_INVENTORY_COLUMNS) + PLAYER_INVENTORY_SLOT_OFFSET;
                int x = xOffset.intValue() + (column * SLOT_PIXEL_SIZE);
                int y = yOffset.intValue() + (row * SLOT_PIXEL_SIZE);

                addSlot(new Slot(playerInventory, index, x, y));
            }
        }
    }

    private void drawHotbarSlots(PlayerInventory playerInventory, MutableInt xOffset, MutableInt yOffset) {
        yOffset.add((3 * SLOT_PIXEL_SIZE) + PLAYER_INVENTORY_HOTBAR_SEPARATOR_PIXELS);

        for (int slot = 0; slot < 9; slot++) {
            int x = xOffset.intValue() + (slot * SLOT_PIXEL_SIZE);
            int y = yOffset.intValue();

            addSlot(new Slot(playerInventory, slot, x, y));
        }
    }

    public static BeGuildContainerScreenHandler create(int syncId, PlayerInventory playerInventory, BeGuildScreenHandlerPayload payload) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_SCREEN_HANDLER, syncId, playerInventory, payload.rows(), payload.columns(), payload);
    }

    /**
     * Moves items quickly in the container when interacting with the player's inventory.
     *
     * @param player The player interacting with the container.
     * @param slotIndex   The slot index.
     * @return ItemStack representing the moved item.
     */
    @Override
    public ItemStack quickMove(PlayerEntity player, int slotIndex) {
        ItemStack itemStack = ItemStack.EMPTY;

        // Get the Slot at the specified index.
        Slot slot = slots.get(slotIndex);

        // Check if the Slot is not null and contains a stack.
        if (slot.hasStack()) {
            ItemStack itemStack2 = slot.getStack(); // Get the ItemStack from the Slot.
            itemStack = itemStack2.copy(); // Create a copy of the ItemStack.

            // Determine the range for item insertion based on the slot index.
            if (slotIndex < (rows * columns)) {
                // Insert item into the player's inventory.
                if (!insertItem(itemStack2, rows * columns, slots.size(), true)) {
                    return ItemStack.EMPTY; // Return an empty ItemStack if insertion fails.
                }
            } else {
                // Insert item into the container's inventory.
                if (!insertItem(itemStack2, 0, rows * columns, false)) {
                    return ItemStack.EMPTY; // Return an empty ItemStack if insertion fails.
                }
            }

            // Update Slot state after item transfer.
            if (itemStack2.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return itemStack; // Return the ItemStack representing the moved item.
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}
