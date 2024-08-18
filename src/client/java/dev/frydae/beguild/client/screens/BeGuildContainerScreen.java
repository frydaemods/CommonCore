package dev.frydae.beguild.client.screens;

import dev.frydae.beguild.client.render.ScreenTexturePiece;
import dev.frydae.beguild.screens.BeGuildContainerScreenHandler;
import dev.frydae.beguild.screens.BeGuildScreenHandlerPayload;
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

        BeGuildScreenHandlerPayload payload = handler.getPayload();

        this.backgroundWidth = payload.width();
        this.backgroundHeight = payload.height();
        this.playerInventoryTitleY = payload.playerInventoryTitleY();
        this.playerInventoryTitleX = payload.playerInventoryTitleX();
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
