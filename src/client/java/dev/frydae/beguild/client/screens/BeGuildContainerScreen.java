package dev.frydae.beguild.client.screens;

import dev.frydae.beguild.client.render.ScreenTexturePiece;
import dev.frydae.beguild.screens.BeGuildContainerScreenHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public final class BeGuildContainerScreen extends UIBaseScreen<BeGuildContainerScreenHandler> {
    private static ScreenTexturePiece slotPiece;

    public BeGuildContainerScreen(BeGuildContainerScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);

        PacketByteBuf buf = handler.getBuf();
        this.backgroundWidth = buf.readInt();
        this.backgroundHeight = buf.readInt();
        this.playerInventoryTitleY = buf.readInt();
        this.playerInventoryTitleX = buf.readInt();
    }

    @Override
    protected void loadUIParts() {
        slotPiece = new ScreenTexturePiece(loadContainerPiece("slot"), 18, 18);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        super.drawBackground(context, delta, mouseX, mouseY);

        for (Slot slot : handler.slots) {
            slotPiece.withInitial(context, x, y)
                    .xOffset(slot.x - 1)
                    .yOffset(slot.y - 1)
                    .drawTexture();
        }
    }
}
