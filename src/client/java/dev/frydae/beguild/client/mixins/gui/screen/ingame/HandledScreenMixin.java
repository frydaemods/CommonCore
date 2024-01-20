package dev.frydae.beguild.client.mixins.gui.screen.ingame;

import dev.frydae.beguild.client.render.BeGuildRendering;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(HandledScreen.class)
public class HandledScreenMixin {
    @ModifyConstant(
            method = "drawForeground",
            constant = @Constant(intValue = 0x404040)
    )
    public int modifyHandledScreenTextColor(int color) {
        return BeGuildRendering.getHandledScreenTextColor();
    }
}
