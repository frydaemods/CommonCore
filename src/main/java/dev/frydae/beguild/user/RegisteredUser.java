package dev.frydae.beguild.user;

import com.mojang.authlib.GameProfile;
import dev.frydae.beguild.BeGuildCommon;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.minecraft.network.packet.c2s.common.SyncedClientOptions;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.UUID;

@Getter @Setter
@RequiredArgsConstructor
public class RegisteredUser {
    public static final Integer SERVER_OWNER_ID = 0;

    private Integer userId;
    private String name;
    private final UUID uuid;
    private Long lastLogin;
    private Long firstLogin;
    private boolean op;

    @Nullable private ServerPlayerEntity player;

    /**
     * Either returns the player object in this user, or creates a fake player object if the player is not online.
     *
     * @return a {@link ServerPlayerEntity player}
     */
    public ServerPlayerEntity getPlayer() {
        if (player != null) {
            return player;
        }

        MinecraftServer server = BeGuildCommon.getServer();
        GameProfile profile = new GameProfile(uuid, "Player Name");

        ServerPlayerEntity fakePlayer = server.getPlayerManager().getPlayer(profile.getId());

        if (fakePlayer == null) {
            fakePlayer = server.getPlayerManager().createPlayer(profile, SyncedClientOptions.createDefault());
            server.getPlayerManager().loadPlayerData(fakePlayer);
        }

        return fakePlayer;
    }

    public boolean isServerOwner() {
        return Objects.equals(userId, SERVER_OWNER_ID);
    }
}
