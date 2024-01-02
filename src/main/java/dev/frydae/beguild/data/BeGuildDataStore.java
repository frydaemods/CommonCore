package dev.frydae.beguild.data;

import dev.frydae.beguild.BeGuildCommon;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public abstract class BeGuildDataStore extends PersistentState {
    public static <T extends PersistentState> T get(Type<T> type, @NotNull String id) {
        MinecraftServer server = BeGuildCommon.getServer();
        PersistentStateManager persistentStateManager = server.getWorld(World.OVERWORLD).getPersistentStateManager();

        T state = persistentStateManager.getOrCreate(type, id);

        state.markDirty();

        return state;
    }
}
