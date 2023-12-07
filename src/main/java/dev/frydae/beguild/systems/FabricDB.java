package dev.frydae.beguild.systems;

import co.aikar.idb.*;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.logging.Logger;

/**
 * Originally this was written by Aikar: <a href="https://github.com/aikar/db/blob/master/bukkit/src/main/java/co/aikar/idb/BukkitDB.java">BukkitDB.java</a>
 *
 * Adapted for Fabric mods.
 */
public class FabricDB {
    private static PooledDatabaseOptions getRecommendedOptions(
            @NotNull String user, @NotNull String pass, @NotNull String schema, @NotNull String hostAndPort,
            @Nullable String poolName, @Nullable Logger logger
    ) {
        String displayName = poolName != null ? poolName : "Database";

        DatabaseOptions options = DatabaseOptions
                .builder()
                .poolName(displayName)
                .logger(logger != null ? logger : Logger.getLogger(displayName))
                .mysql(user, pass, schema, hostAndPort)
                .build();

        return PooledDatabaseOptions
                .builder()
                .options(options)
                .build();
    }

    @CanIgnoreReturnValue
    public static Database createDatabase(@NotNull String user, @NotNull String pass, @NotNull String schema, @NotNull String hostAndPort, @NotNull Consumer<Database> closeConsumer) {
        return createDatabase(user, pass, schema, hostAndPort, closeConsumer, true, null, null);
    }

    @CanIgnoreReturnValue
    public static Database createDatabase(
            @NotNull String user, @NotNull String pass, @NotNull String schema, @NotNull String hostAndPort,
            @NotNull Consumer<Database> closeConsumer, boolean setGlobal, @Nullable String poolName, @Nullable Logger logger
    ) {
        HikariPooledDatabase database = new HikariPooledDatabase(getRecommendedOptions(user, pass, schema, hostAndPort, poolName, logger));

        if (setGlobal) {
            DB.setGlobalDatabase(database);
        }

        closeConsumer.accept(database);

        return database;
    }
}
