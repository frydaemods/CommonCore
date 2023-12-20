package dev.frydae.beguild.systems;

import com.google.common.collect.Maps;
import dev.frydae.beguild.BeGuildCommon;
import dev.frydae.beguild.utils.Location;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class BlockDataPersistence extends PersistentState {
    public HashMap<Location, ItemStack> items = Maps.newHashMap();

    private static final Type<BlockDataPersistence> type = new Type<>(
            BlockDataPersistence::new,
            BlockDataPersistence::createFromNbt,
            null
    );

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        NbtCompound itemNbt = new NbtCompound();

        items.forEach((location, itemStack) -> {
            if (location != null && itemStack != null) {
                itemNbt.put(location.toString(), itemStack.writeNbt(new NbtCompound()));
            }
        });

        nbt.put("blocks", itemNbt);

        return nbt;
    }

    private static BlockDataPersistence createFromNbt(NbtCompound nbt) {
        BlockDataPersistence state = new BlockDataPersistence();

        NbtCompound items = nbt.getCompound("blocks");

        items.getKeys().forEach(key -> {
            Location location = Location.fromString(key);
            state.items.put(location, ItemStack.fromNbt(items.getCompound(key)));
        });

        return state;
    }

    public static BlockDataPersistence get() {
        MinecraftServer server = BeGuildCommon.getSingleton().getServer();
        PersistentStateManager persistentStateManager = server.getWorld(World.OVERWORLD).getPersistentStateManager();
        BlockDataPersistence state = persistentStateManager.getOrCreate(type, "beguild_block_meta");

        state.markDirty();

        return state;
    }

    public static ItemStack getFromLocation(@NotNull Location location) {
        HashMap<Location, ItemStack> map = get().items;

        final ItemStack orDefault = map.getOrDefault(location, null);

        if (orDefault != null) {
            System.out.printf("Removing %s from %s%n", orDefault, location);
            map.remove(location, orDefault);
        }

        return orDefault;
    }

    public static void storeBlockMeta(@NotNull Location location, @NotNull ItemStack stack) {
        ItemStack copy = stack.copy();
        copy.setCount(1);

        System.out.printf("Putting %s at %s%n", copy, location);
        get().items.put(location, copy);
    }
}
