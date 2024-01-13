package dev.frydae.beguild.screens;

import com.mojang.logging.LogUtils;
import lombok.Getter;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import org.slf4j.Logger;

public final class BeGuildScreens {
    @Getter private static final Logger logger = LogUtils.getLogger();
    public static final int SLOT_PIXEL_SIZE = 18;

    // region 9 Row Handlers
    public static BeGuildContainerScreenHandler create9x1(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_9X1, syncId, playerInventory, 1, 9, buf);
    }

    public static BeGuildContainerScreenHandler create9x2(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_9X2, syncId, playerInventory, 2, 9, buf);
    }

    public static BeGuildContainerScreenHandler create9x3(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_9X3, syncId, playerInventory, 3, 9, buf);
    }

    public static BeGuildContainerScreenHandler create9x4(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_9X4, syncId, playerInventory, 4, 9, buf);
    }

    public static BeGuildContainerScreenHandler create9x5(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_9X5, syncId, playerInventory, 5, 9, buf);
    }

    public static BeGuildContainerScreenHandler create9x6(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_9X6, syncId, playerInventory, 6, 9, buf);
    }

    public static BeGuildContainerScreenHandler create9x7(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_9X7, syncId, playerInventory, 7, 9, buf);
    }

    public static BeGuildContainerScreenHandler create9x8(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_9X8, syncId, playerInventory, 8, 9, buf);
    }

    public static BeGuildContainerScreenHandler create9x9(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_9X9, syncId, playerInventory, 9, 9, buf);
    }

    public static BeGuildContainerScreenHandler create9x10(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_9X10, syncId, playerInventory, 10, 9, buf);
    }

    public static BeGuildContainerScreenHandler create9x11(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_9X11, syncId, playerInventory, 11, 9, buf);
    }

    public static BeGuildContainerScreenHandler create9x12(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_9X12, syncId, playerInventory, 12, 9, buf);
    }

    public static BeGuildContainerScreenHandler create9x13(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_9X13, syncId, playerInventory, 13, 9, buf);
    }

    public static BeGuildContainerScreenHandler create9x14(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_9X14, syncId, playerInventory, 14, 9, buf);
    }

    public static BeGuildContainerScreenHandler create9x15(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_9X15, syncId, playerInventory, 15, 9, buf);
    }
    // endregion
    // region 10 Row Handlers
    public static BeGuildContainerScreenHandler create10x1(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_10X1, syncId, playerInventory, 1, 10, buf);
    }

    public static BeGuildContainerScreenHandler create10x2(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_10X2, syncId, playerInventory, 2, 10, buf);
    }

    public static BeGuildContainerScreenHandler create10x3(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_10X3, syncId, playerInventory, 3, 10, buf);
    }

    public static BeGuildContainerScreenHandler create10x4(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_10X4, syncId, playerInventory, 4, 10, buf);
    }

    public static BeGuildContainerScreenHandler create10x5(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_10X5, syncId, playerInventory, 5, 10, buf);
    }

    public static BeGuildContainerScreenHandler create10x6(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_10X6, syncId, playerInventory, 6, 10, buf);
    }

    public static BeGuildContainerScreenHandler create10x7(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_10X7, syncId, playerInventory, 7, 10, buf);
    }

    public static BeGuildContainerScreenHandler create10x8(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_10X8, syncId, playerInventory, 8, 10, buf);
    }

    public static BeGuildContainerScreenHandler create10x9(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_10X9, syncId, playerInventory, 9, 10, buf);
    }

    public static BeGuildContainerScreenHandler create10x10(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_10X10, syncId, playerInventory, 10, 10, buf);
    }

    public static BeGuildContainerScreenHandler create10x11(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_10X11, syncId, playerInventory, 11, 10, buf);
    }

    public static BeGuildContainerScreenHandler create10x12(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_10X12, syncId, playerInventory, 12, 10, buf);
    }

    public static BeGuildContainerScreenHandler create10x13(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_10X13, syncId, playerInventory, 13, 10, buf);
    }

    public static BeGuildContainerScreenHandler create10x14(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_10X14, syncId, playerInventory, 14, 10, buf);
    }

    public static BeGuildContainerScreenHandler create10x15(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_10X15, syncId, playerInventory, 15, 10, buf);
    }
    // endregion
    // region 11 Row Handlers
    public static BeGuildContainerScreenHandler create11x1(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_11X1, syncId, playerInventory, 1, 11, buf);
    }

    public static BeGuildContainerScreenHandler create11x2(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_11X2, syncId, playerInventory, 2, 11, buf);
    }

    public static BeGuildContainerScreenHandler create11x3(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_11X3, syncId, playerInventory, 3, 11, buf);
    }

    public static BeGuildContainerScreenHandler create11x4(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_11X4, syncId, playerInventory, 4, 11, buf);
    }

    public static BeGuildContainerScreenHandler create11x5(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_11X5, syncId, playerInventory, 5, 11, buf);
    }

    public static BeGuildContainerScreenHandler create11x6(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_11X6, syncId, playerInventory, 6, 11, buf);
    }

    public static BeGuildContainerScreenHandler create11x7(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_11X7, syncId, playerInventory, 7, 11, buf);
    }

    public static BeGuildContainerScreenHandler create11x8(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_11X8, syncId, playerInventory, 8, 11, buf);
    }

    public static BeGuildContainerScreenHandler create11x9(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_11X9, syncId, playerInventory, 9, 11, buf);
    }

    public static BeGuildContainerScreenHandler create11x10(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_11X10, syncId, playerInventory, 10, 11, buf);
    }

    public static BeGuildContainerScreenHandler create11x11(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_11X11, syncId, playerInventory, 11, 11, buf);
    }

    public static BeGuildContainerScreenHandler create11x12(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_11X12, syncId, playerInventory, 12, 11, buf);
    }

    public static BeGuildContainerScreenHandler create11x13(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_11X13, syncId, playerInventory, 13, 11, buf);
    }

    public static BeGuildContainerScreenHandler create11x14(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_11X14, syncId, playerInventory, 14, 11, buf);
    }

    public static BeGuildContainerScreenHandler create11x15(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_11X15, syncId, playerInventory, 15, 11, buf);
    }
    // endregion
    // region 12 Row Handlers
    public static BeGuildContainerScreenHandler create12x1(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_12X1, syncId, playerInventory, 1, 12, buf);
    }

    public static BeGuildContainerScreenHandler create12x2(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_12X2, syncId, playerInventory, 2, 12, buf);
    }

    public static BeGuildContainerScreenHandler create12x3(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_12X3, syncId, playerInventory, 3, 12, buf);
    }

    public static BeGuildContainerScreenHandler create12x4(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_12X4, syncId, playerInventory, 4, 12, buf);
    }

    public static BeGuildContainerScreenHandler create12x5(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_12X5, syncId, playerInventory, 5, 12, buf);
    }

    public static BeGuildContainerScreenHandler create12x6(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_12X6, syncId, playerInventory, 6, 12, buf);
    }

    public static BeGuildContainerScreenHandler create12x7(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_12X7, syncId, playerInventory, 7, 12, buf);
    }

    public static BeGuildContainerScreenHandler create12x8(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_12X8, syncId, playerInventory, 8, 12, buf);
    }

    public static BeGuildContainerScreenHandler create12x9(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_12X9, syncId, playerInventory, 9, 12, buf);
    }

    public static BeGuildContainerScreenHandler create12x10(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_12X10, syncId, playerInventory, 10, 12, buf);
    }

    public static BeGuildContainerScreenHandler create12x11(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_12X11, syncId, playerInventory, 11, 12, buf);
    }

    public static BeGuildContainerScreenHandler create12x12(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_12X12, syncId, playerInventory, 12, 12, buf);
    }

    public static BeGuildContainerScreenHandler create12x13(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_12X13, syncId, playerInventory, 13, 12, buf);
    }

    public static BeGuildContainerScreenHandler create12x14(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_12X14, syncId, playerInventory, 14, 12, buf);
    }

    public static BeGuildContainerScreenHandler create12x15(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_12X15, syncId, playerInventory, 15, 12, buf);
    }
    // endregion
    // region 13 Row Handlers
    public static BeGuildContainerScreenHandler create13x1(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_13X1, syncId, playerInventory, 1, 13, buf);
    }

    public static BeGuildContainerScreenHandler create13x2(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_13X2, syncId, playerInventory, 2, 13, buf);
    }

    public static BeGuildContainerScreenHandler create13x3(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_13X3, syncId, playerInventory, 3, 13, buf);
    }

    public static BeGuildContainerScreenHandler create13x4(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_13X4, syncId, playerInventory, 4, 13, buf);
    }

    public static BeGuildContainerScreenHandler create13x5(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_13X5, syncId, playerInventory, 5, 13, buf);
    }

    public static BeGuildContainerScreenHandler create13x6(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_13X6, syncId, playerInventory, 6, 13, buf);
    }

    public static BeGuildContainerScreenHandler create13x7(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_13X7, syncId, playerInventory, 7, 13, buf);
    }

    public static BeGuildContainerScreenHandler create13x8(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_13X8, syncId, playerInventory, 8, 13, buf);
    }

    public static BeGuildContainerScreenHandler create13x9(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_13X9, syncId, playerInventory, 9, 13, buf);
    }

    public static BeGuildContainerScreenHandler create13x10(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_13X10, syncId, playerInventory, 10, 13, buf);
    }

    public static BeGuildContainerScreenHandler create13x11(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_13X11, syncId, playerInventory, 11, 13, buf);
    }

    public static BeGuildContainerScreenHandler create13x12(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_13X12, syncId, playerInventory, 12, 13, buf);
    }

    public static BeGuildContainerScreenHandler create13x13(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_13X13, syncId, playerInventory, 13, 13, buf);
    }

    public static BeGuildContainerScreenHandler create13x14(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_13X14, syncId, playerInventory, 14, 13, buf);
    }

    public static BeGuildContainerScreenHandler create13x15(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_13X15, syncId, playerInventory, 15, 13, buf);
    }
    // endregion
    // region 14 Row Handlers
    public static BeGuildContainerScreenHandler create14x1(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_14X1, syncId, playerInventory, 1, 14, buf);
    }

    public static BeGuildContainerScreenHandler create14x2(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_14X2, syncId, playerInventory, 2, 14, buf);
    }

    public static BeGuildContainerScreenHandler create14x3(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_14X3, syncId, playerInventory, 3, 14, buf);
    }

    public static BeGuildContainerScreenHandler create14x4(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_14X4, syncId, playerInventory, 4, 14, buf);
    }

    public static BeGuildContainerScreenHandler create14x5(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_14X5, syncId, playerInventory, 5, 14, buf);
    }

    public static BeGuildContainerScreenHandler create14x6(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_14X6, syncId, playerInventory, 6, 14, buf);
    }

    public static BeGuildContainerScreenHandler create14x7(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_14X7, syncId, playerInventory, 7, 14, buf);
    }

    public static BeGuildContainerScreenHandler create14x8(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_14X8, syncId, playerInventory, 8, 14, buf);
    }

    public static BeGuildContainerScreenHandler create14x9(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_14X9, syncId, playerInventory, 9, 14, buf);
    }

    public static BeGuildContainerScreenHandler create14x10(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_14X10, syncId, playerInventory, 10, 14, buf);
    }

    public static BeGuildContainerScreenHandler create14x11(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_14X11, syncId, playerInventory, 11, 14, buf);
    }

    public static BeGuildContainerScreenHandler create14x12(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_14X12, syncId, playerInventory, 12, 14, buf);
    }

    public static BeGuildContainerScreenHandler create14x13(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_14X13, syncId, playerInventory, 13, 14, buf);
    }

    public static BeGuildContainerScreenHandler create14x14(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_14X14, syncId, playerInventory, 14, 14, buf);
    }

    public static BeGuildContainerScreenHandler create14x15(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_14X15, syncId, playerInventory, 15, 14, buf);
    }
    // endregion
    // region 15 Row Handlers
    public static BeGuildContainerScreenHandler create15x1(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_15X1, syncId, playerInventory, 1, 15, buf);
    }

    public static BeGuildContainerScreenHandler create15x2(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_15X2, syncId, playerInventory, 2, 15, buf);
    }

    public static BeGuildContainerScreenHandler create15x3(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_15X3, syncId, playerInventory, 3, 15, buf);
    }

    public static BeGuildContainerScreenHandler create15x4(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_15X4, syncId, playerInventory, 4, 15, buf);
    }

    public static BeGuildContainerScreenHandler create15x5(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_15X5, syncId, playerInventory, 5, 15, buf);
    }

    public static BeGuildContainerScreenHandler create15x6(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_15X6, syncId, playerInventory, 6, 15, buf);
    }

    public static BeGuildContainerScreenHandler create15x7(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_15X7, syncId, playerInventory, 7, 15, buf);
    }

    public static BeGuildContainerScreenHandler create15x8(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_15X8, syncId, playerInventory, 8, 15, buf);
    }

    public static BeGuildContainerScreenHandler create15x9(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_15X9, syncId, playerInventory, 9, 15, buf);
    }

    public static BeGuildContainerScreenHandler create15x10(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_15X10, syncId, playerInventory, 10, 15, buf);
    }

    public static BeGuildContainerScreenHandler create15x11(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_15X11, syncId, playerInventory, 11, 15, buf);
    }

    public static BeGuildContainerScreenHandler create15x12(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_15X12, syncId, playerInventory, 12, 15, buf);
    }

    public static BeGuildContainerScreenHandler create15x13(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_15X13, syncId, playerInventory, 13, 15, buf);
    }

    public static BeGuildContainerScreenHandler create15x14(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_15X14, syncId, playerInventory, 14, 15, buf);
    }

    public static BeGuildContainerScreenHandler create15x15(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        return new BeGuildContainerScreenHandler(BeGuildScreenHandlerType.BEGUILD_15X15, syncId, playerInventory, 15, 15, buf);
    }

    public static void registerScreens() {
        // region 9 Row Screens
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_9X1, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_9X2, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_9X3, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_9X4, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_9X5, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_9X6, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_9X7, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_9X8, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_9X9, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_9X10, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_9X11, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_9X12, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_9X13, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_9X14, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_9X15, BeGuildContainerScreen::new);
        // endregion
        // region 10 Row Screens
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_10X1, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_10X2, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_10X3, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_10X4, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_10X5, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_10X6, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_10X7, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_10X8, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_10X9, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_10X10, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_10X11, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_10X12, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_10X13, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_10X14, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_10X15, BeGuildContainerScreen::new);
        // endregion
        // region 11 Row Screens
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_11X1, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_11X2, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_11X3, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_11X4, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_11X5, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_11X6, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_11X7, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_11X8, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_11X9, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_11X10, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_11X11, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_11X12, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_11X13, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_11X14, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_11X15, BeGuildContainerScreen::new);
        // endregion
        // region 12 Row Screens
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_12X1, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_12X2, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_12X3, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_12X4, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_12X5, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_12X6, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_12X7, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_12X8, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_12X9, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_12X10, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_12X11, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_12X12, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_12X13, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_12X14, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_12X15, BeGuildContainerScreen::new);
        // endregion
        // region 13 Row Screens
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_13X1, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_13X2, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_13X3, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_13X4, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_13X5, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_13X6, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_13X7, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_13X8, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_13X9, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_13X10, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_13X11, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_13X12, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_13X13, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_13X14, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_13X15, BeGuildContainerScreen::new);
        // endregion
        // region 14 Row Screens
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_14X1, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_14X2, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_14X3, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_14X4, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_14X5, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_14X6, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_14X7, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_14X8, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_14X9, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_14X10, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_14X11, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_14X12, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_14X13, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_14X14, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_14X15, BeGuildContainerScreen::new);
        // endregion
        // region 15 Row Screens
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_15X1, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_15X2, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_15X3, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_15X4, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_15X5, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_15X6, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_15X7, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_15X8, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_15X9, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_15X10, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_15X11, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_15X12, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_15X13, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_15X14, BeGuildContainerScreen::new);
        HandledScreens.register(BeGuildScreenHandlerType.BEGUILD_15X15, BeGuildContainerScreen::new);
        // endregion
    }
    // endregion
}
