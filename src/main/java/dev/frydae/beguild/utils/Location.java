package dev.frydae.beguild.utils;

import com.sk89q.worldedit.math.BlockVector3;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public record Location(World world, double x, double y, double z) {
    public Location(World world, BlockVector3 pos1) {
        this(world, pos1.getX(), pos1.getY(), pos1.getZ());
    }

    public double getBlockX() {
        return Math.floor(x);
    }

    public double getBlockY() {
        return Math.floor(y);
    }

    public double getBlockZ() {
        return Math.floor(z);
    }

    public Location add(double x, double y, double z) {
        return new Location(world, this.x + x, this.y + y, this.z + z);
    }

    public Location subtract(double x, double y, double z) {
        return new Location(world, this.x - x, this.y - y, this.z - z);
    }

    public boolean isWithin(@NotNull Location lowLoc, @NotNull Location highLoc) {
        return lowLoc.x() <= x && x <= highLoc.x() &&
                lowLoc.y() <= y && y <= highLoc.y() &&
                lowLoc.z() <= z && z <= highLoc.z();
    }
}
