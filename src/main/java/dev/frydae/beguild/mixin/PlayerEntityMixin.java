package dev.frydae.beguild.mixin;

import net.fabricmc.fabric.impl.transfer.item.SpecialLogicInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EnderChestInventory;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Debug(export = true)
@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Shadow protected EnderChestInventory enderChestInventory;

    @Inject(method = "getEnderChestInventory", at = @At("HEAD"))
    public void supressFabricEnderChanges(CallbackInfoReturnable<EnderChestInventory> cir) {
        ((SpecialLogicInventory) enderChestInventory).fabric_setSuppress(true);
    }
}
