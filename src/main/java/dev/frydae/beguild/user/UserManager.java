package dev.frydae.beguild.user;

import com.google.common.collect.Lists;
import dev.frydae.beguild.BeGuildCommon;
import dev.frydae.beguild.data.BeGuildDataStore;
import dev.frydae.beguild.data.Caches;
import dev.frydae.beguild.utils.TimeUtil;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

public final class UserManager extends BeGuildDataStore {
    private final List<RegisteredUser> users = Lists.newCopyOnWriteArrayList();

    private static final Type<UserManager> type = new Type<>(
            UserManager::new,
            UserManager::createFromNbt,
            null
    );

    public static RegisteredUser getUser(@NotNull String name) {
        return getUser("name", name);
    }

    public static RegisteredUser getUser(@NotNull UUID uuid) {
        return getUser("uuid", uuid);
    }

    public static RegisteredUser getUser(@NotNull Integer userId) {
        return getUser("userId", userId);
    }

    public static RegisteredUser getUser(@NotNull ServerPlayerEntity player) {
        return getUser(player.getUuid());
    }

    private static RegisteredUser getUser(@NotNull String variable, @NotNull Object o) {
        RegisteredUser foundUser = switch (variable) {
            case "name" -> Caches.userNameCache.getIfPresent(o);
            case "uuid" -> Caches.userUuidCache.getIfPresent(o);
            case "userId" -> Caches.userIdCache.getIfPresent(o);
            default -> null;
        };

        if (foundUser != null) {
            return foundUser;
        }

        for (RegisteredUser registeredUser : get().users) {
            Object field = switch (variable) {
                case "name" -> registeredUser.getName();
                case "uuid" -> registeredUser.getUuid();
                case "userId" -> registeredUser.getUserId();
                default -> null;
            };

            if (field != null && field.equals(o)) {
                ServerPlayerEntity player = BeGuildCommon.getServer().getPlayerManager().getPlayer(registeredUser.getUuid());

                // Might be null, which is okay
                registeredUser.setPlayer(player);

                refreshCaches(registeredUser);

                return registeredUser;
            }
        }

        return null;
    }

    public static void refreshCaches(@NotNull RegisteredUser user) {
        Caches.userNameCache.put(user.getName(), user);
        Caches.userUuidCache.put(user.getUuid(), user);
        Caches.userIdCache.put(user.getUserId(), user);
    }

    public static UserManager get() {
        return get(type, "beguild_users");
    }

    private static UserManager createFromNbt(NbtCompound nbt) {
        UserManager store = new UserManager();

        nbt.getKeys().forEach(key -> {
            NbtCompound userCompound = nbt.getCompound(key);

            RegisteredUser user = new RegisteredUser(userCompound.getUuid("uuid"));

            user.setUserId(userCompound.getInt("userId"));
            user.setName(userCompound.getString("name"));
            user.setLastLogin(userCompound.getLong("lastLogin"));
            user.setFirstLogin(userCompound.getLong("firstLogin"));
            user.setOp(userCompound.getBoolean("op"));

            store.users.add(user);
        });

        return store;
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        NbtCompound compound = new NbtCompound();

        for (RegisteredUser user : users) {
            NbtCompound userCompound = new NbtCompound();

            userCompound.putInt("userId", user.getUserId());
            userCompound.putString("name", user.getName());
            userCompound.putUuid("uuid", user.getUuid());
            userCompound.putLong("lastLogin", user.getLastLogin());
            userCompound.putLong("firstLogin", user.getFirstLogin());
            userCompound.putBoolean("op", user.isOp());

            compound.put(String.valueOf(user.getUserId()), userCompound);
        }

        return compound;
    }

    public static void addOrUpdatePlayer(ServerPlayerEntity player) {
        RegisteredUser user = getUser(player.getUuid());

        if (user == null) {
            user = new RegisteredUser(player.getUuid());

            user.setUserId(getNextUserId());
            user.setFirstLogin(TimeUtil.timestamp());

            get().users.add(user);
        }

        user.setName(player.getName().getString());
        user.setLastLogin(TimeUtil.timestamp());
        user.setOp(player.hasPermissionLevel(1));
        user.setPlayer(player);

        refreshCaches(user);
    }

    private static Integer getNextUserId() {
        return get().users.stream()
                .mapToInt(RegisteredUser::getUserId)
                .max()
                .orElse(0) + 1;
    }

    public static List<String> getUsers(Integer lastDays) {
        List<String> names = Lists.newArrayList();

        if (lastDays != null) {
            long timeSince = TimeUtil.timestamp() - TimeUtil.DAY.inSeconds(lastDays);

            get().users.stream()
                    .filter(registeredUser -> registeredUser.getLastLogin() > timeSince)
                    .map(RegisteredUser::getName)
                    .forEach(names::add);
        } else {
            get().users.stream().map(RegisteredUser::getName).forEach(names::add);
        }

        return names;
    }
}
