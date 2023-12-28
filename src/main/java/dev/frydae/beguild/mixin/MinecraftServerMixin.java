package dev.frydae.beguild.mixin;

import dev.frydae.beguild.utils.taskchain.TaskManager;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BooleanSupplier;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
    @Inject(
            method = "tick",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;tickWorlds(Ljava/util/function/BooleanSupplier;)V")
    )
    public void tickManagerStep(BooleanSupplier shouldKeepTicking, CallbackInfo ci) {
        TaskManager.getInstance().tick();
    }
}
