package dev.frydae.beguild.screens;

import com.google.common.collect.Maps;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Map;

import static dev.frydae.beguild.screens.BeGuildScreens.SLOT_PIXEL_SIZE;

public final class BeGuildContainerScreen extends HandledScreen<BeGuildContainerScreenHandler> implements ScreenHandlerProvider<BeGuildContainerScreenHandler> {
    private final int rows;
    private final int columns;
    private final Identifier texture;

    private static final int BACKGROUND_HEIGHT = 384;

    private static final int INVENTORY_TITLE_HEIGHT = 17;
    private static final int PLAYER_INVENTORY_HEIGHT = 96;

    // 17 is the number of pixels between the top of the texture and the first container row
    // 97 is the number of pixels between the bottom of the texture and the last container row
    private static final int BACKGROUND_OFFSET_PIXELS = INVENTORY_TITLE_HEIGHT + PLAYER_INVENTORY_HEIGHT + 1;

    private static final Map<Integer, Integer> backgroundWidths = Maps.newHashMap();

    static {
        backgroundWidths.put(9, 184);
        backgroundWidths.put(10, 202);
        backgroundWidths.put(11, 220);
        backgroundWidths.put(12, 238);
        backgroundWidths.put(13, 256);
        backgroundWidths.put(14, 274);
        backgroundWidths.put(15, 292);
    }

    public BeGuildContainerScreen(BeGuildContainerScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);

        this.rows = handler.getRows();
        this.columns = handler.getColumns();

        this.texture = new Identifier("beguild", "textures/gui/container/generic_" + this.columns + ".png");

        this.backgroundHeight = BACKGROUND_OFFSET_PIXELS + (rows * 18);
        this.backgroundWidth = backgroundWidths.get(columns);
        this.playerInventoryTitleY = this.backgroundHeight - 94;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - this.backgroundHeight) / 2;

        context.drawTexture(texture, x, y, 0, 0, backgroundWidth, (rows * SLOT_PIXEL_SIZE) + INVENTORY_TITLE_HEIGHT, backgroundWidth, BACKGROUND_HEIGHT);
        context.drawTexture(texture, x, y + (rows * SLOT_PIXEL_SIZE) + INVENTORY_TITLE_HEIGHT, 0, BACKGROUND_HEIGHT - PLAYER_INVENTORY_HEIGHT, backgroundWidth, PLAYER_INVENTORY_HEIGHT, backgroundWidth, BACKGROUND_HEIGHT);
    }
}
