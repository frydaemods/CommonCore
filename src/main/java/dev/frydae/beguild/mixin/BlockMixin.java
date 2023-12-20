package dev.frydae.beguild.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import dev.frydae.beguild.systems.BlockDataPersistence;
import dev.frydae.beguild.utils.Location;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.function.Consumer;

@Mixin(Block.class)
public class BlockMixin {
    @Shadow
    public static void dropStack(World world, BlockPos pos, ItemStack stack) {
    }

    @ModifyArg(
            method = "dropStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)V",
            at = @At(value = "INVOKE", target = "Ljava/util/List;forEach(Ljava/util/function/Consumer;)V")
    )
    private static Consumer<ItemStack> setMeta(Consumer<ItemStack> original, @Local(argsOnly = true) BlockState state, @Local(argsOnly = true) World world, @Local(argsOnly = true) BlockPos pos, @Local(argsOnly = true) BlockEntity blockEntity) {
        return stack -> {
            ItemStack foundStack = BlockDataPersistence.getFromLocation(new Location(world, pos));

            if (foundStack != null) {
                stack = foundStack;
            }

            dropStack(world, pos, stack);
        };
    }
}
