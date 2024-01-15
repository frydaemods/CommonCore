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

import static dev.frydae.beguild.data.Constants.INVENTORY_SLOT_X_OFFSET_PIXELS;
import static dev.frydae.beguild.data.Constants.getPlayerInventoryOffsetPixels;

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
    private final PacketByteBuf buf;

    public BeGuildContainerScreenHandler(ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, int rows, int columns) {
        this(type, syncId, playerInventory, new SimpleInventory(columns * rows), rows, columns, PacketByteBufs.empty());
    }

    public BeGuildContainerScreenHandler(ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, int rows, int columns, PacketByteBuf buf) {
        this(type, syncId, playerInventory, new SimpleInventory(columns * rows), rows, columns, buf);
    }

    public BeGuildContainerScreenHandler(ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, Inventory inventory, int rows, int columns) {
        this(type, syncId, playerInventory, inventory, rows, columns, PacketByteBufs.empty());
    }

    public BeGuildContainerScreenHandler(ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, Inventory inventory, int rows, int columns, PacketByteBuf buf) {
        super(type, syncId);

        checkSize(inventory, rows * columns);

        this.inventory = inventory;
        this.rows = rows;
        this.columns = columns;
        this.buf = buf;

        inventory.onOpen(playerInventory.player);

        int inventoryPixelOffset = (columns - PLAYER_INVENTORY_COLUMNS) * PLAYER_INVENTORY_SLOT_OFFSET;

        // Calculate the offset for the player inventory based on the number of container rows
        int playerInventoryOffset = (this.rows - 4) * 18 + 1;

        for (int row = 0; row < this.rows; row++) {
            for (int column = 0; column < this.columns; column++) {
                int index = column + (row * columns);
                int x = INVENTORY_SLOT_X_OFFSET_PIXELS + (column * SLOT_PIXEL_SIZE);
                int y = SLOT_Y_OFFSET_PIXELS + (row * SLOT_PIXEL_SIZE);

                addSlot(new Slot(inventory, index, x, y));
            }
        }

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < PLAYER_INVENTORY_COLUMNS; column++) {
                int index = column + (row * PLAYER_INVENTORY_COLUMNS) + PLAYER_INVENTORY_SLOT_OFFSET;
                int x = INVENTORY_SLOT_X_OFFSET_PIXELS + getPlayerInventoryOffsetPixels(columns) + (column * SLOT_PIXEL_SIZE);
                int y = 103 + (row * SLOT_PIXEL_SIZE) + playerInventoryOffset;

                addSlot(new Slot(playerInventory, index, x, y));
            }
        }

        for (int slot = 0; slot < 9; ++slot) {
            int x = INVENTORY_SLOT_X_OFFSET_PIXELS + inventoryPixelOffset + (slot * SLOT_PIXEL_SIZE);
            int y = 161 + playerInventoryOffset;

            addSlot(new Slot(playerInventory, slot, x, y));
        }
    }

    public static BeGuildContainerScreenHandler create(int rows, int columns, int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.get(rows, columns), syncId, playerInventory, rows, columns, buf);
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
