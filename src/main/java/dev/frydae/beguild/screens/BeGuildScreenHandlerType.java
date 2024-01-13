package dev.frydae.beguild.screens;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BeGuildScreenHandlerType {
    private static final Table<Integer, Integer, ExtendedScreenHandlerType<BeGuildContainerScreenHandler>> handlers = HashBasedTable.create();

    // region 9 Row Handlers
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_9X1 = register("beguild_9x1", 9, 1, BeGuildScreens::create9x1);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_9X2 = register("beguild_9x2", 9, 2, BeGuildScreens::create9x2);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_9X3 = register("beguild_9x3", 9, 3, BeGuildScreens::create9x3);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_9X4 = register("beguild_9x4", 9, 4, BeGuildScreens::create9x4);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_9X5 = register("beguild_9x5", 9, 5, BeGuildScreens::create9x5);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_9X6 = register("beguild_9x6", 9, 6, BeGuildScreens::create9x6);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_9X7 = register("beguild_9x7", 9, 7, BeGuildScreens::create9x7);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_9X8 = register("beguild_9x8", 9, 8, BeGuildScreens::create9x8);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_9X9 = register("beguild_9x9", 9, 9, BeGuildScreens::create9x9);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_9X10 = register("beguild_9x10", 9, 10, BeGuildScreens::create9x10);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_9X11 = register("beguild_9x11", 9, 11, BeGuildScreens::create9x11);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_9X12 = register("beguild_9x12", 9, 12, BeGuildScreens::create9x12);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_9X13 = register("beguild_9x13", 9, 13, BeGuildScreens::create9x13);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_9X14 = register("beguild_9x14", 9, 14, BeGuildScreens::create9x14);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_9X15 = register("beguild_9x15", 9, 15, BeGuildScreens::create9x15);
    // endregion
    // region 10 Row Handlers
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_10X1 = register("beguild_10x1", 10, 1, BeGuildScreens::create10x1);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_10X2 = register("beguild_10x2", 10, 2, BeGuildScreens::create10x2);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_10X3 = register("beguild_10x3", 10, 3, BeGuildScreens::create10x3);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_10X4 = register("beguild_10x4", 10, 4, BeGuildScreens::create10x4);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_10X5 = register("beguild_10x5", 10, 5, BeGuildScreens::create10x5);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_10X6 = register("beguild_10x6", 10, 6, BeGuildScreens::create10x6);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_10X7 = register("beguild_10x7", 10, 7, BeGuildScreens::create10x7);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_10X8 = register("beguild_10x8", 10, 8, BeGuildScreens::create10x8);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_10X9 = register("beguild_10x9", 10, 9, BeGuildScreens::create10x9);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_10X10 = register("beguild_10x10", 10, 10, BeGuildScreens::create10x10);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_10X11 = register("beguild_10x11", 10, 11, BeGuildScreens::create10x11);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_10X12 = register("beguild_10x12", 10, 12, BeGuildScreens::create10x12);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_10X13 = register("beguild_10x13", 10, 13, BeGuildScreens::create10x13);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_10X14 = register("beguild_10x14", 10, 14, BeGuildScreens::create10x14);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_10X15 = register("beguild_10x15", 10, 15, BeGuildScreens::create10x15);
    // endregion
    // region 11 Row Handlers
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_11X1 = register("beguild_11x1", 11, 1, BeGuildScreens::create11x1);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_11X2 = register("beguild_11x2", 11, 2, BeGuildScreens::create11x2);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_11X3 = register("beguild_11x3", 11, 3, BeGuildScreens::create11x3);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_11X4 = register("beguild_11x4", 11, 4, BeGuildScreens::create11x4);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_11X5 = register("beguild_11x5", 11, 5, BeGuildScreens::create11x5);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_11X6 = register("beguild_11x6", 11, 6, BeGuildScreens::create11x6);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_11X7 = register("beguild_11x7", 11, 7, BeGuildScreens::create11x7);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_11X8 = register("beguild_11x8", 11, 8, BeGuildScreens::create11x8);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_11X9 = register("beguild_11x9", 11, 9, BeGuildScreens::create11x9);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_11X10 = register("beguild_11x10", 11, 10, BeGuildScreens::create11x10);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_11X11 = register("beguild_11x11", 11, 11, BeGuildScreens::create11x11);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_11X12 = register("beguild_11x12", 11, 12, BeGuildScreens::create11x12);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_11X13 = register("beguild_11x13", 11, 13, BeGuildScreens::create11x13);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_11X14 = register("beguild_11x14", 11, 14, BeGuildScreens::create11x14);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_11X15 = register("beguild_11x15", 11, 15, BeGuildScreens::create11x15);
    // endregion
    // region 12 Row Handlers
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_12X1 = register("beguild_12x1", 12, 1, BeGuildScreens::create12x1);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_12X2 = register("beguild_12x2", 12, 2, BeGuildScreens::create12x2);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_12X3 = register("beguild_12x3", 12, 3, BeGuildScreens::create12x3);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_12X4 = register("beguild_12x4", 12, 4, BeGuildScreens::create12x4);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_12X5 = register("beguild_12x5", 12, 5, BeGuildScreens::create12x5);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_12X6 = register("beguild_12x6", 12, 6, BeGuildScreens::create12x6);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_12X7 = register("beguild_12x7", 12, 7, BeGuildScreens::create12x7);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_12X8 = register("beguild_12x8", 12, 8, BeGuildScreens::create12x8);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_12X9 = register("beguild_12x9", 12, 9, BeGuildScreens::create12x9);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_12X10 = register("beguild_12x10", 12, 10, BeGuildScreens::create12x10);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_12X11 = register("beguild_12x11", 12, 11, BeGuildScreens::create12x11);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_12X12 = register("beguild_12x12", 12, 12, BeGuildScreens::create12x12);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_12X13 = register("beguild_12x13", 12, 13, BeGuildScreens::create12x13);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_12X14 = register("beguild_12x14", 12, 14, BeGuildScreens::create12x14);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_12X15 = register("beguild_12x15", 12, 15, BeGuildScreens::create12x15);
    // endregion
    // region 13 Row Handlers
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_13X1 = register("beguild_13x1", 13, 1, BeGuildScreens::create13x1);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_13X2 = register("beguild_13x2", 13, 2, BeGuildScreens::create13x2);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_13X3 = register("beguild_13x3", 13, 3, BeGuildScreens::create13x3);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_13X4 = register("beguild_13x4", 13, 4, BeGuildScreens::create13x4);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_13X5 = register("beguild_13x5", 13, 5, BeGuildScreens::create13x5);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_13X6 = register("beguild_13x6", 13, 6, BeGuildScreens::create13x6);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_13X7 = register("beguild_13x7", 13, 7, BeGuildScreens::create13x7);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_13X8 = register("beguild_13x8", 13, 8, BeGuildScreens::create13x8);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_13X9 = register("beguild_13x9", 13, 9, BeGuildScreens::create13x9);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_13X10 = register("beguild_13x10", 13, 10, BeGuildScreens::create13x10);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_13X11 = register("beguild_13x11", 13, 11, BeGuildScreens::create13x11);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_13X12 = register("beguild_13x12", 13, 12, BeGuildScreens::create13x12);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_13X13 = register("beguild_13x13", 13, 13, BeGuildScreens::create13x13);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_13X14 = register("beguild_13x14", 13, 14, BeGuildScreens::create13x14);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_13X15 = register("beguild_13x15", 13, 15, BeGuildScreens::create13x15);
    // endregion
    // region 14 Row Handlers
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_14X1 = register("beguild_14x1", 14, 1, BeGuildScreens::create14x1);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_14X2 = register("beguild_14x2", 14, 2, BeGuildScreens::create14x2);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_14X3 = register("beguild_14x3", 14, 3, BeGuildScreens::create14x3);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_14X4 = register("beguild_14x4", 14, 4, BeGuildScreens::create14x4);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_14X5 = register("beguild_14x5", 14, 5, BeGuildScreens::create14x5);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_14X6 = register("beguild_14x6", 14, 6, BeGuildScreens::create14x6);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_14X7 = register("beguild_14x7", 14, 7, BeGuildScreens::create14x7);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_14X8 = register("beguild_14x8", 14, 8, BeGuildScreens::create14x8);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_14X9 = register("beguild_14x9", 14, 9, BeGuildScreens::create14x9);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_14X10 = register("beguild_14x10", 14, 10, BeGuildScreens::create14x10);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_14X11 = register("beguild_14x11", 14, 11, BeGuildScreens::create14x11);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_14X12 = register("beguild_14x12", 14, 12, BeGuildScreens::create14x12);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_14X13 = register("beguild_14x13", 14, 13, BeGuildScreens::create14x13);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_14X14 = register("beguild_14x14", 14, 14, BeGuildScreens::create14x14);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_14X15 = register("beguild_14x15", 14, 15, BeGuildScreens::create14x15);
    // endregion
    // region 15 Row Handlers
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_15X1 = register("beguild_15x1", 15, 1, BeGuildScreens::create15x1);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_15X2 = register("beguild_15x2", 15, 2, BeGuildScreens::create15x2);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_15X3 = register("beguild_15x3", 15, 3, BeGuildScreens::create15x3);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_15X4 = register("beguild_15x4", 15, 4, BeGuildScreens::create15x4);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_15X5 = register("beguild_15x5", 15, 5, BeGuildScreens::create15x5);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_15X6 = register("beguild_15x6", 15, 6, BeGuildScreens::create15x6);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_15X7 = register("beguild_15x7", 15, 7, BeGuildScreens::create15x7);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_15X8 = register("beguild_15x8", 15, 8, BeGuildScreens::create15x8);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_15X9 = register("beguild_15x9", 15, 9, BeGuildScreens::create15x9);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_15X10 = register("beguild_15x10", 15, 10, BeGuildScreens::create15x10);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_15X11 = register("beguild_15x11", 15, 11, BeGuildScreens::create15x11);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_15X12 = register("beguild_15x12", 15, 12, BeGuildScreens::create15x12);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_15X13 = register("beguild_15x13", 15, 13, BeGuildScreens::create15x13);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_15X14 = register("beguild_15x14", 15, 14, BeGuildScreens::create15x14);
    public static final ExtendedScreenHandlerType<BeGuildContainerScreenHandler> BEGUILD_15X15 = register("beguild_15x15", 15, 15, BeGuildScreens::create15x15);
    // endregion

    public static ExtendedScreenHandlerType<BeGuildContainerScreenHandler> get(int rows, int columns) {
        return handlers.get(rows, columns);
    }

    private static ExtendedScreenHandlerType<BeGuildContainerScreenHandler> register(String id, int columns, int rows, ExtendedScreenHandlerType.ExtendedFactory<BeGuildContainerScreenHandler> factory) {
        ExtendedScreenHandlerType<BeGuildContainerScreenHandler> handler = new ExtendedScreenHandlerType<>(factory);

        Registry.register(Registries.SCREEN_HANDLER, new Identifier("beguild", "container_" + id), handler);

        handlers.put(rows, columns, handler);

        return handler;
    }
}
