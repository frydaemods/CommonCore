package dev.frydae.beguild.user;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

@Getter @Setter
@RequiredArgsConstructor
public class RegisteredUser {
    @Expose private Integer userId;
    @Expose private String name;
    @Expose private final UUID uuid;
    @Expose private Long lastLogin;
    @Expose private Long firstLogin;
    @Expose private boolean op;

    @Nullable private ServerPlayerEntity player;
}
