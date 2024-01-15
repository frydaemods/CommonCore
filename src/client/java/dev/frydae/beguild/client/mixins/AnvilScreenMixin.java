package dev.frydae.beguild.client.mixins;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.frydae.beguild.client.render.BeGuildRendering;
import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import net.minecraft.util.Identifier;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AnvilScreen.class)
public abstract class AnvilScreenMixin {
    @ModifyExpressionValue(
            method = "drawBackground",
            at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screen/ingame/AnvilScreen;TEXT_FIELD_TEXTURE:Lnet/minecraft/util/Identifier;", opcode = Opcodes.GETSTATIC)
    )
    private static Identifier applyDarkTextFieldTexture(Identifier original) {
        return BeGuildRendering.processSpriteTexture(original);
    }

    @ModifyExpressionValue(
            method = "drawBackground",
            at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screen/ingame/AnvilScreen;TEXT_FIELD_DISABLED_TEXTURE:Lnet/minecraft/util/Identifier;", opcode = Opcodes.GETSTATIC)
    )
    private static Identifier applyDarkTextFieldDisabledTexture(Identifier original) {
        return BeGuildRendering.processSpriteTexture(original);
    }

    @ModifyExpressionValue(
            method = "drawInvalidRecipeArrow",
            at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screen/ingame/AnvilScreen;ERROR_TEXTURE:Lnet/minecraft/util/Identifier;", opcode = Opcodes.GETSTATIC)
    )
    private static Identifier applyDarkErrorTexture(Identifier original) {
        return BeGuildRendering.processSpriteTexture(original);
    }

    @ModifyExpressionValue(
            method = "<init>",
            at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screen/ingame/AnvilScreen;TEXTURE:Lnet/minecraft/util/Identifier;", opcode = Opcodes.GETSTATIC)
    )
    private static Identifier applyDarkTexture(Identifier original) {
        return BeGuildRendering.processUITexture(original);
    }
}
