package dev.frydae.beguild.utils;

import lombok.Getter;

@Getter
public enum Direction {
    NORTH(180),
    EAST(-90),
    SOUTH(0),
    WEST(90),
    NORTH_EAST(-135),
    NORTH_WEST(135),
    SOUTH_EAST(-45),
    SOUTH_WEST(45);

    private final int yaw;

    Direction(int yaw) {
        this.yaw = yaw;
    }

    public String getName() {
        return Util.ucfirst(name().toLowerCase().replace("_", " "), true);
    }

    public static Direction getByYaw(double yaw) {
        if (yaw >= -22.5 && yaw < 22.5) {
            return SOUTH;
        } else if (yaw >= 22.5 && yaw < 67.5) {
            return SOUTH_WEST;
        } else if (yaw >= 67.5 && yaw < 112.5) {
            return WEST;
        } else if (yaw >= 112.5 && yaw < 157.5) {
            return NORTH_WEST;
        } else if (yaw >= 157.5 || yaw < -157.5) {
            return NORTH;
        } else if (yaw >= -157.5 && yaw < -112.5) {
            return NORTH_EAST;
        } else if (yaw >= -112.5 && yaw < -67.5) {
            return EAST;
        } else if (yaw >= -67.5 && yaw < -22.5) {
            return SOUTH_EAST;
        } else {
            return SOUTH;
        }
    }
}
