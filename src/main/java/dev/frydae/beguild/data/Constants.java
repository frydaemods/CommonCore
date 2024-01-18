package dev.frydae.beguild.data;

public final class Constants {
    private Constants() {}

    public static final int INVENTORY_TITLE_OFFSET = 10;
    public static final int INVENTORY_BORDER_PIXELS = 16;
    public static final int INVENTORY_SLOT_OFFSET = 7;
    public static final int INVENTORY_SLOT_SIZE = 18;

    public static final int PLAYER_INVENTORY_TITLE_OFFSET = 3;
    public static final int PLAYER_INVENTORY_CONTAINER_SEPARATOR_PIXELS = 14;
    public static final int PLAYER_INVENTORY_HOTBAR_SEPARATOR_PIXELS = 4;
    public static final int PLAYER_INVENTORY_SLOT_OFFSET = 9;
    public static final int PLAYER_INVENTORY_COLUMNS = 9;
    public static int getPlayerInventoryOffsetPixels(int columns) {
        return (columns - PLAYER_INVENTORY_COLUMNS) * PLAYER_INVENTORY_SLOT_OFFSET;
    }
}
