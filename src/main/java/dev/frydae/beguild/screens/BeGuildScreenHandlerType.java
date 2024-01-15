package dev.frydae.beguild.screens;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import lombok.Getter;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BeGuildScreenHandlerType {
    @Getter private static final Table<Integer, Integer, ExtendedScreenHandlerType<BeGuildContainerScreenHandler>> handlers = HashBasedTable.create();

    public static void registerHandlerTypes() {
        for (int rows = 1; rows <= 20; rows++) {
            for (int columns = 9; columns <= 50; columns++) {
                register(rows, columns);
            }
        }
    }

    public static ExtendedScreenHandlerType<BeGuildContainerScreenHandler> get(int rows, int columns) {
        return handlers.get(rows, columns);
    }

    public static void register(int rows, int columns) {
        ExtendedScreenHandlerType.ExtendedFactory<BeGuildContainerScreenHandler> factory = (syncId, playerInventory, buf) -> BeGuildContainerScreenHandler.create(rows, columns, syncId, playerInventory, buf);
        ExtendedScreenHandlerType<BeGuildContainerScreenHandler> handler = new ExtendedScreenHandlerType<>(factory);

        Registry.register(Registries.SCREEN_HANDLER, new Identifier("beguild", "container_" + rows + "x" + columns), handler);

        handlers.put(rows, columns, handler);
    }
}
