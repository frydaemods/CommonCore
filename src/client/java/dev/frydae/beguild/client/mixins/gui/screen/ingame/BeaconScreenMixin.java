package dev.frydae.beguild.client.mixins.gui.screen.ingame;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.frydae.beguild.client.render.BeGuildRendering;
import net.minecraft.client.gui.screen.ingame.BeaconScreen;
import net.minecraft.util.Identifier;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BeaconScreen.class)
public class BeaconScreenMixin {
    @ModifyExpressionValue(
            method = "drawBackground",
            at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screen/ingame/BeaconScreen;TEXTURE:Lnet/minecraft/util/Identifier;", opcode = Opcodes.GETSTATIC)
    )
    public Identifier applyDarkTexture(Identifier original) {
        return BeGuildRendering.processUITexture(original);
    }

    @Mixin(BeaconScreen.BaseButtonWidget.class)
    public static class BaseButtonWidgetMixin {
        @ModifyExpressionValue(
                method = "renderWidget",
                at = {
                        @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screen/ingame/BeaconScreen;BUTTON_DISABLED_TEXTURE:Lnet/minecraft/util/Identifier;", opcode = Opcodes.GETSTATIC),
                        @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screen/ingame/BeaconScreen;BUTTON_SELECTED_TEXTURE:Lnet/minecraft/util/Identifier;", opcode = Opcodes.GETSTATIC),
                        @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screen/ingame/BeaconScreen;BUTTON_HIGHLIGHTED_TEXTURE:Lnet/minecraft/util/Identifier;", opcode = Opcodes.GETSTATIC),
                        @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screen/ingame/BeaconScreen;BUTTON_TEXTURE:Lnet/minecraft/util/Identifier;", opcode = Opcodes.GETSTATIC)
                }
        )
        public Identifier applyDarkTexture(Identifier original) {
            return BeGuildRendering.processSpriteTexture(original);
        }
    }
}
