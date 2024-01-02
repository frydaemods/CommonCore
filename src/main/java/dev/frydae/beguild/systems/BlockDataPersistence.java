package dev.frydae.beguild.systems;

import com.google.common.collect.Maps;
import dev.frydae.beguild.data.BeGuildDataStore;
import dev.frydae.beguild.utils.Location;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class BlockDataPersistence extends BeGuildDataStore {
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
        return get(type, "beguild_block_meta");
    }

    public static ItemStack getFromLocation(@NotNull Location location) {
        HashMap<Location, ItemStack> map = get().items;

        ItemStack foundStack = map.getOrDefault(location, null);

        if (foundStack != null) {
            map.remove(location, foundStack);
        }

        return foundStack;
    }

    public static void storeBlockMeta(@NotNull Location location, @NotNull ItemStack stack) {
        ItemStack copy = stack.copy();
        copy.setCount(1);

        get().items.put(location, copy);
    }
}
