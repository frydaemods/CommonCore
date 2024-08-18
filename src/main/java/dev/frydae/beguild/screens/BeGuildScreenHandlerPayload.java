package dev.frydae.beguild.screens;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record BeGuildScreenHandlerPayload(int rows, int columns, int width, int height, int playerInventoryTitleY, int playerInventoryTitleX) implements CustomPayload {
    public static final CustomPayload.Id<BeGuildScreenHandlerPayload> ID = new CustomPayload.Id<>(Identifier.of("beguild", "screen_handler_packet"));

    public static final PacketCodec<RegistryByteBuf, BeGuildScreenHandlerPayload> CODEC = PacketCodec.tuple(
            PacketCodecs.INTEGER, BeGuildScreenHandlerPayload::rows,
            PacketCodecs.INTEGER, BeGuildScreenHandlerPayload::columns,
            PacketCodecs.INTEGER, BeGuildScreenHandlerPayload::width,
            PacketCodecs.INTEGER, BeGuildScreenHandlerPayload::height,
            PacketCodecs.INTEGER, BeGuildScreenHandlerPayload::playerInventoryTitleY,
            PacketCodecs.INTEGER, BeGuildScreenHandlerPayload::playerInventoryTitleX,
            BeGuildScreenHandlerPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
