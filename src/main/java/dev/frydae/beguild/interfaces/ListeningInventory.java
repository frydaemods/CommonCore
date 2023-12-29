package dev.frydae.beguild.interfaces;

import net.minecraft.inventory.InventoryChangedListener;

public interface ListeningInventory {
    void beguild$addListener(InventoryChangedListener listener);

    void beguild$removeListener(InventoryChangedListener listener);
}
