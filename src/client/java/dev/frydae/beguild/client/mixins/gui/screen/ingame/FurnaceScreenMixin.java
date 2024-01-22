package dev.frydae.beguild.client.mixins.gui.screen.ingame;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.frydae.beguild.client.render.BeGuildRendering;
import net.minecraft.client.gui.screen.ingame.FurnaceScreen;
import net.minecraft.util.Identifier;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FurnaceScreen.class)
public class FurnaceScreenMixin {
    @ModifyExpressionValue(
            method = "<init>",
            at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screen/ingame/FurnaceScreen;TEXTURE:Lnet/minecraft/util/Identifier;", opcode = Opcodes.GETSTATIC)
    )
    private static Identifier processFurnaceTexture(Identifier original) {
        return BeGuildRendering.processUITexture(original);
    }

    @ModifyExpressionValue(
            method = "<init>",
            at = {
                    @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screen/ingame/FurnaceScreen;LIT_PROGRESS_TEXTURE:Lnet/minecraft/util/Identifier;", opcode = Opcodes.GETSTATIC),
                    @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screen/ingame/FurnaceScreen;BURN_PROGRESS_TEXTURE:Lnet/minecraft/util/Identifier;", opcode = Opcodes.GETSTATIC),
            }
    )
    private static Identifier processFurnaceSprites(Identifier original) {
        return BeGuildRendering.processSpriteTexture(original);
    }
}
