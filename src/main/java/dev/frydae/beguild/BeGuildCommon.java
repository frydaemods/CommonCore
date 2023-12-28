package dev.frydae.beguild;

import co.aikar.taskchain.TaskChain;
import co.aikar.taskchain.TaskChainFactory;
import dev.frydae.beguild.events.ServerEvents;
import dev.frydae.beguild.user.RegisteredUser;
import dev.frydae.beguild.user.UserManager;
import dev.frydae.beguild.utils.taskchain.FabricTaskChainFactory;
import dev.frydae.commands.FabricCommandCompletions;
import dev.frydae.commands.FabricCommandContexts;
import dev.frydae.commands.FabricCommandManager;
import dev.frydae.commands.IllegalCommandException;
import lombok.Getter;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.MinecraftServer;
import xyz.nucleoid.server.translations.api.language.ServerLanguageDefinition;
import xyz.nucleoid.server.translations.impl.ServerTranslations;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class BeGuildCommon implements DedicatedServerModInitializer {
    private static volatile BeGuildCommon singleton;

    @Getter private MinecraftServer server;
    @Getter private TaskChainFactory taskChainFactory;

    public static void registerCommands() {
        registerContexts();
        registerCompletions();

        FabricCommandManager.registerCommands();
    }

    private static void registerContexts() {
        FabricCommandContexts commandContexts = FabricCommandManager.getSingleton().getCommandContexts();

        commandContexts.registerContext(RegisteredUser.class, c -> {
            RegisteredUser user = UserManager.getUser(c.getParameterText());

            if (user == null) {
                throw new IllegalCommandException("User: " + c.getParameterText() + " cannot be found.");
            }

            return user;
        });
    }

    private static void registerCompletions() {
        FabricCommandCompletions commandCompletions = FabricCommandManager.getSingleton().getCommandCompletions();

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

    public static Logger getLogger() {
        return Logger.getLogger("BeGuild");
    }

    @Override
    public void onInitializeServer() {
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            UserManager.addOrUpdatePlayer(handler.getPlayer());
        });

        registerLifecycleEvents();

        registerCommands();

        ServerLanguageDefinition language = ServerTranslations.INSTANCE.getLanguageDefinition("en_us");
        ServerTranslations.INSTANCE.setSystemLanguage(language);
    }

    private void registerLifecycleEvents() {
        ServerLifecycleEvents.SERVER_STARTING.register((server) -> {
            getSingleton().setServer(server);

            ServerEvents.POST_SINGLETON.getInvoker().onPostSingletonCallback(server);
        });

        ServerEvents.POST_SINGLETON.register(server -> {
            getSingleton().taskChainFactory = FabricTaskChainFactory.create(server);

            UserManager.loadUsers();
        });

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(UserManager::saveUsers, 1, 1, TimeUnit.MINUTES);

        ServerLifecycleEvents.SERVER_STOPPING.register((server) -> {
            scheduler.shutdownNow();
            UserManager.saveUsers();
        });
    }

    private void setServer(MinecraftServer server) {
        this.server = server;
    }

    public static BeGuildCommon getSingleton() {
        if (singleton == null) {
            synchronized (BeGuildCommon.class) {
                if (singleton == null) {
                    singleton = new BeGuildCommon();
                }
            }
        }

        return singleton;
    }

    public static <T> TaskChain<T> newChain() {
        return getSingleton().taskChainFactory.newChain();
    }
    public static <T> TaskChain<T> newSharedChain(String name) {
        return getSingleton().taskChainFactory.newSharedChain(name);
    }

}
