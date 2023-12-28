package dev.frydae.beguild.utils.taskchain;

import co.aikar.taskchain.AsyncQueue;
import co.aikar.taskchain.GameInterface;
import co.aikar.taskchain.TaskChainAsyncQueue;
import co.aikar.taskchain.TaskChainFactory;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;

import java.util.concurrent.TimeUnit;

public class FabricTaskChainFactory extends TaskChainFactory {
    private FabricTaskChainFactory(MinecraftServer server, AsyncQueue asyncQueue) {
        super(new FabricGameInterface(server, asyncQueue));
    }

    public static TaskChainFactory create(MinecraftServer server) {
        return new FabricTaskChainFactory(server, new TaskChainAsyncQueue());
    }

    private static class FabricGameInterface implements GameInterface {
        private final MinecraftServer server;
        private final AsyncQueue asyncQueue;

        public FabricGameInterface(MinecraftServer server, AsyncQueue asyncQueue) {
            this.server = server;
            this.asyncQueue = asyncQueue;
        }

        @Override
        public boolean isMainThread() {
            return server.isOnThread();
        }

        @Override
        public AsyncQueue getAsyncQueue() {
            return asyncQueue;
        }

        @Override
        public void postToMain(Runnable run) {
            if (server.isRunning()) {
                server.executeSync(run);
            } else {
                run.run();
            }
        }

        @Override
        public void scheduleTask(int waitTicks, Runnable run) {
            if (server.isRunning()) {
                TaskManager.getInstance().addTask(waitTicks, run);
            } else {
                run.run();
            }
        }

        @Override
        public void registerShutdownHandler(TaskChainFactory factory) {
            ServerLifecycleEvents.SERVER_STOPPING.register((server1) -> factory.shutdown(60, TimeUnit.SECONDS));
        }
    }
}
