package dev.frydae.beguild;

import co.aikar.taskchain.TaskChain;
import co.aikar.taskchain.TaskChainFactory;
import com.mojang.logging.LogUtils;
import dev.frydae.beguild.loader.BeGuildMod;
import dev.frydae.beguild.systems.BlockDataPersistence;
import dev.frydae.beguild.user.RegisteredUser;
import dev.frydae.beguild.user.UserManager;
import dev.frydae.beguild.utils.taskchain.FabricTaskChainFactory;
import dev.frydae.commands.FabricCommandCompletions;
import dev.frydae.commands.FabricCommandConditions;
import dev.frydae.commands.FabricCommandContexts;
import dev.frydae.commands.IllegalCommandException;
import lombok.Getter;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import xyz.nucleoid.server.translations.api.language.ServerLanguageDefinition;
import xyz.nucleoid.server.translations.impl.ServerTranslations;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class BeGuildCommon extends BeGuildMod {
    @Getter private static ScheduledExecutorService scheduler;
    @Getter private static Logger logger = LogUtils.getLogger();

    @Getter private static MinecraftServer server;
    @Getter private static TaskChainFactory taskChainFactory;

    @Override
    public void registerCommands(FabricCommandCompletions commandCompletions, FabricCommandContexts commandContexts, FabricCommandConditions commandConditions) {
        registerContexts(commandContexts);
        registerCompletions(commandCompletions);
    }

    private static void registerContexts(FabricCommandContexts commandContexts) {
        commandContexts.registerContext(RegisteredUser.class, c -> {
            RegisteredUser user = UserManager.getUser(c.getParameterText());

            if (user == null) {
                throw new IllegalCommandException("User: " + c.getParameterText() + " cannot be found.");
            }

            return user;
        });
    }

    private static void registerCompletions(FabricCommandCompletions commandCompletions) {
        commandCompletions.registerSmartCompletion("allplayers", c -> {
            Integer lastDays = null;

            for (Map.Entry<String, String> stringStringEntry : c.getConfigs().entrySet()) {
                try {
                    lastDays = Integer.parseInt(stringStringEntry.getKey());
                } catch (NumberFormatException ignored) {}
            }

            List<String> users = UserManager.getUsers(lastDays);

            if (c.hasConfig("other")) {
                users.remove(c.getContext().getSource().getName());
            }

            return users;
        });
    }

    @Override
    public void onInitialize() {
        scheduler = Executors.newScheduledThreadPool(1);
    }

    @Override
    public void earliestStarting(MinecraftServer server) {
        BeGuildCommon.server = server;
    }

    @Override
    public void onStarting(MinecraftServer server) {
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server1) -> {
            UserManager.addOrUpdatePlayer(handler.getPlayer());
        });

        ServerLanguageDefinition language = ServerTranslations.INSTANCE.getLanguageDefinition("en_us");
        ServerTranslations.INSTANCE.setSystemLanguage(language);

        taskChainFactory = FabricTaskChainFactory.create(server);
    }

    @Override
    public void onStarted(MinecraftServer server) {
        UserManager.get();
        BlockDataPersistence.get();
    }

    @Override
    public void onStopping(MinecraftServer server) {
        scheduler.shutdownNow();
    }

    public static <T> TaskChain<T> newChain() {
        return taskChainFactory.newChain();
    }
    public static <T> TaskChain<T> newSharedChain(String name) {
        return taskChainFactory.newSharedChain(name);
    }

}
