package dev.frydae.beguild.mixin;

import dev.frydae.beguild.events.ServerLifecycleEvents;
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

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;setupServer()Z"), method = "runServer")
    private void beforeSetupServer(CallbackInfo info) {
        ServerLifecycleEvents.EARLIEST_SERVER_STARTING.invoker().onServerStarting((MinecraftServer) (Object) this);
        ServerLifecycleEvents.PRE_SERVER_STARTING.invoker().onServerStarting((MinecraftServer) (Object) this);
        ServerLifecycleEvents.SERVER_STARTING.invoker().onServerStarting((MinecraftServer) (Object) this);
        ServerLifecycleEvents.POST_SERVER_STARTING.invoker().onServerStarting((MinecraftServer) (Object) this);
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;createMetadata()Lnet/minecraft/server/ServerMetadata;", ordinal = 0), method = "runServer")
    private void afterSetupServer(CallbackInfo info) {
        ServerLifecycleEvents.PRE_SERVER_STARTED.invoker().onServerStarted((MinecraftServer) (Object) this);
        ServerLifecycleEvents.SERVER_STARTED.invoker().onServerStarted((MinecraftServer) (Object) this);
        ServerLifecycleEvents.POST_SERVER_STARTED.invoker().onServerStarted((MinecraftServer) (Object) this);
    }

    @Inject(at = @At("HEAD"), method = "shutdown")
    private void beforeShutdownServer(CallbackInfo info) {
        ServerLifecycleEvents.PRE_SERVER_STOPPING.invoker().onServerStopping((MinecraftServer) (Object) this);
        ServerLifecycleEvents.SERVER_STOPPING.invoker().onServerStopping((MinecraftServer) (Object) this);
        ServerLifecycleEvents.POST_SERVER_STOPPING.invoker().onServerStopping((MinecraftServer) (Object) this);
    }

    @Inject(at = @At("TAIL"), method = "shutdown")
    private void afterShutdownServer(CallbackInfo info) {
        ServerLifecycleEvents.PRE_SERVER_STOPPED.invoker().onServerStopped((MinecraftServer) (Object) this);
        ServerLifecycleEvents.SERVER_STOPPED.invoker().onServerStopped((MinecraftServer) (Object) this);
        ServerLifecycleEvents.POST_SERVER_STOPPED.invoker().onServerStopped((MinecraftServer) (Object) this);
    }
}
