package dev.frydae.beguild.mixin;

import com.google.common.collect.Lists;
import dev.frydae.beguild.interfaces.ListeningInventory;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.InventoryChangedListener;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(PlayerInventory.class)
public class PlayerInventoryMixin implements ListeningInventory {
    @Unique
    @Nullable
    private List<InventoryChangedListener> beguild$listeners;

    @Override
    public void beguild$addListener(InventoryChangedListener listener) {
        if (this.beguild$listeners == null) {
            this.beguild$listeners = Lists.newArrayList();
        }
        this.beguild$listeners.add(listener);
    }

    @Override
    public void beguild$removeListener(InventoryChangedListener listener) {
        if (this.beguild$listeners != null) {
            this.beguild$listeners.remove(listener);
        }
    }

    @Inject(method = "markDirty", at = @At("TAIL"))
    public void markDirty(CallbackInfo ci) {
        if (this.beguild$listeners != null) {
            for (InventoryChangedListener inventoryChangedListener : this.beguild$listeners) {
                inventoryChangedListener.onInventoryChanged((PlayerInventory) (Object) this);
            }
        }
    }
}
