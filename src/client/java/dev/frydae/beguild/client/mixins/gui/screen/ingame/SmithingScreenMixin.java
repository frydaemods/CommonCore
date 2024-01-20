package dev.frydae.beguild.client.mixins.gui.screen.ingame;

import dev.frydae.beguild.client.render.BeGuildRendering;
import net.minecraft.client.gui.screen.ingame.SmithingScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(SmithingScreen.class)
public class SmithingScreenMixin {
    @ModifyArg(
            method = "<init>",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Identifier;<init>(Ljava/lang/String;)V")
    )
    private static String applyDarkTexture(String original) {
        return BeGuildRendering.processUITexture(original);
    }
}
