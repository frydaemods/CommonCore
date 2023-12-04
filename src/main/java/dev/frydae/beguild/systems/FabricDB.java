package dev.frydae.beguild.systems;

import co.aikar.idb.*;
import dev.frydae.beguild.BeGuildCommon;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import org.jetbrains.annotations.NotNull;

/**
 * Originally this was written by Aikar: <a href="https://github.com/aikar/db/blob/master/bukkit/src/main/java/co/aikar/idb/BukkitDB.java">BukkitDB.java</a>
 *
 * Adapted for Fabric mods.
 */
public class FabricDB {
    private static PooledDatabaseOptions getOptions(@NotNull String user, @NotNull String pass, @NotNull String schema, @NotNull String hostAndPort) {
        DatabaseOptions options = DatabaseOptions
                .builder()
                .poolName("Fabric DB")
                .logger(BeGuildCommon.getLogger())
                .mysql(user, pass, schema, hostAndPort)
                .build();

        return PooledDatabaseOptions
                .builder()
                .options(options)
                .build();
    }

    public static Database createDatabase(@NotNull String user, @NotNull String pass, @NotNull String schema, @NotNull String hostAndPort) {
        HikariPooledDatabase database = new HikariPooledDatabase(getOptions(user, pass, schema, hostAndPort));

        DB.setGlobalDatabase(database);

        ServerLifecycleEvents.SERVER_STOPPING.register(server -> database.close());

        return database;
    }
}
