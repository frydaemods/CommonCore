package dev.frydae.beguild.screens;

import com.google.common.collect.Maps;
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

import java.util.Map;

import static dev.frydae.beguild.screens.BeGuildScreens.SLOT_PIXEL_SIZE;

@Getter
public class BeGuildContainerScreenHandler extends ScreenHandler {
    private static final int SLOT_X_OFFSET_PIXELS = 12;
    private static final int SLOT_Y_OFFSET_PIXELS = 18;

    private static final Map<Integer, Integer> inventoryPixelOffsets = Maps.newHashMap();
    private static final int PLAYER_INVENTORY_SLOT_OFFSET = 9;
    private static final int PLAYER_INVENTORY_COLUMNS = 9;

    static {
        inventoryPixelOffsets.put(9, 0);
        inventoryPixelOffsets.put(10, 9);
        inventoryPixelOffsets.put(11, 18);
        inventoryPixelOffsets.put(12, 27);
        inventoryPixelOffsets.put(13, 36);
        inventoryPixelOffsets.put(14, 45);
        inventoryPixelOffsets.put(15, 54);
    }

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

        // Calculate the offset for the player inventory based on the number of container rows
        int playerInventoryOffset = (this.rows - 4) * 18;

        for (int row = 0; row < this.rows; row++) {
            for (int column = 0; column < this.columns; column++) {
                int index = column + (row * columns);
                int x = SLOT_X_OFFSET_PIXELS + (column * SLOT_PIXEL_SIZE);
                int y = SLOT_Y_OFFSET_PIXELS + (row * SLOT_PIXEL_SIZE);

                addSlot(new Slot(inventory, index, x, y));
            }
        }

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < PLAYER_INVENTORY_COLUMNS; column++) {
                int index = column + (row * PLAYER_INVENTORY_COLUMNS) + PLAYER_INVENTORY_SLOT_OFFSET;
                int x = SLOT_X_OFFSET_PIXELS + inventoryPixelOffsets.get(columns) + (column * SLOT_PIXEL_SIZE);
                int y = 103 + (row * SLOT_PIXEL_SIZE) + playerInventoryOffset;

                addSlot(new Slot(playerInventory, index, x, y));
            }
        }

        for (int slot = 0; slot < 9; ++slot) {
            int x = SLOT_X_OFFSET_PIXELS + inventoryPixelOffsets.get(columns) + (slot * SLOT_PIXEL_SIZE);
            int y = 161 + playerInventoryOffset;

            addSlot(new Slot(playerInventory, slot, x, y));
        }
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
