package dev.frydae.beguild.client.mixins.gui.screen.ingame;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.frydae.beguild.client.render.BeGuildRendering;
import net.minecraft.client.gui.screen.ingame.BlastFurnaceScreen;
import net.minecraft.util.Identifier;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BlastFurnaceScreen.class)
public class BlastFurnaceMixin {
    @ModifyExpressionValue(
            method = "<init>",
            at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screen/ingame/BlastFurnaceScreen;TEXTURE:Lnet/minecraft/util/Identifier;", opcode = Opcodes.GETSTATIC)
    )
    private static Identifier processBlastFurnaceTexture(Identifier original) {
        return BeGuildRendering.processUITexture(original);
    }

    @ModifyExpressionValue(
            method = "<init>",
            at = {
                    @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screen/ingame/BlastFurnaceScreen;LIT_PROGRESS_TEXTURE:Lnet/minecraft/util/Identifier;", opcode = Opcodes.GETSTATIC),
                    @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screen/ingame/BlastFurnaceScreen;BURN_PROGRESS_TEXTURE:Lnet/minecraft/util/Identifier;", opcode = Opcodes.GETSTATIC),
            }
    )
    private static Identifier processBlastFurnaceSprites(Identifier original) {
        return BeGuildRendering.processSpriteTexture(original);
    }
}
