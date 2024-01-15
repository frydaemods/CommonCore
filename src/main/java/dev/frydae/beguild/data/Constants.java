package dev.frydae.beguild.data;

public final class Constants {
    private Constants() {}

    public static final int INVENTORY_SLOT_PIXEL_SIZE = 18;
    public static final int INVENTORY_TITLE_HEIGHT_PIXELS = 17;
    public static final int INVENTORY_VERTICAL_BORDER_PIXELS = 7;
    public static final int INVENTORY_SLOT_X_OFFSET_PIXELS = INVENTORY_VERTICAL_BORDER_PIXELS + 1;

    public static final int PLAYER_INVENTORY_CONTAINER_SEPARATOR_PIXELS = 14;
    public static final int PLAYER_INVENTORY_HOTBAR_SEPARATOR_PIXELS = 4;
    public static final int PLAYER_INVENTORY_BOTTOM_PIXELS = 7;
    public static final int PLAYER_INVENTORY_HEIGHT = PLAYER_INVENTORY_CONTAINER_SEPARATOR_PIXELS + PLAYER_INVENTORY_HOTBAR_SEPARATOR_PIXELS + PLAYER_INVENTORY_BOTTOM_PIXELS + (4 * INVENTORY_SLOT_PIXEL_SIZE);
    public static final int PLAYER_INVENTORY_SLOT_OFFSET = 9;
    public static final int PLAYER_INVENTORY_COLUMNS = 9;
    public static int getPlayerInventoryOffsetPixels(int columns) {
        return (columns - PLAYER_INVENTORY_COLUMNS) * PLAYER_INVENTORY_SLOT_OFFSET;
    }
}
