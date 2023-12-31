package dev.frydae.beguild.user;

import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;
import dev.frydae.beguild.BeGuildCommon;
import dev.frydae.beguild.ConfigManager;
import dev.frydae.beguild.data.Caches;
import dev.frydae.beguild.utils.TimeUtil;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

public final class UserManager {
    private static final List<RegisteredUser> USER_CACHE = Lists.newArrayList();

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

        for (RegisteredUser registeredUser : USER_CACHE) {
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

    public static void loadUsers() {
        List<RegisteredUser> users = ConfigManager.loadConfig("BeGuild", "registered_users", new TypeToken<List<RegisteredUser>>(){}.getType());

        if (users != null && !users.isEmpty()) {
            USER_CACHE.clear();
            USER_CACHE.addAll(users);
        }
    }

    public static void saveUsers() {
        ConfigManager.saveConfig("BeGuild", "registered_users", USER_CACHE);
    }

    public static void addOrUpdatePlayer(ServerPlayerEntity player) {
        RegisteredUser user = getUser(player.getUuid());

        if (user == null) {
            user = new RegisteredUser(player.getUuid());

            user.setUserId(getNextUserId());
            user.setFirstLogin(TimeUtil.timestamp());

            USER_CACHE.add(user);
        }

        user.setName(player.getName().getString());
        user.setLastLogin(TimeUtil.timestamp());
        user.setOp(player.hasPermissionLevel(1));
        user.setPlayer(player);

        refreshCaches(user);
    }

    private static Integer getNextUserId() {
        return USER_CACHE.stream()
                .mapToInt(RegisteredUser::getUserId)
                .max()
                .orElse(0) + 1;
    }

    public static List<String> getUsers(Integer lastDays) {
        List<String> names = Lists.newArrayList();

        if (lastDays != null) {
            long timeSince = TimeUtil.timestamp() - TimeUtil.DAY.inSeconds(lastDays);

            USER_CACHE.stream()
                    .filter(registeredUser -> registeredUser.getLastLogin() > timeSince)
                    .map(RegisteredUser::getName)
                    .forEach(names::add);
        } else {
            USER_CACHE.stream().map(RegisteredUser::getName).forEach(names::add);
        }

        return names;
    }
}
