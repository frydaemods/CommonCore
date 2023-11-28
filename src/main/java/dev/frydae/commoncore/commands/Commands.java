package dev.frydae.commoncore.commands;

import dev.frydae.commands.FabricCommandCompletions;
import dev.frydae.commands.FabricCommandContexts;
import dev.frydae.commands.FabricCommandManager;
import dev.frydae.commands.IllegalCommandException;
import dev.frydae.commoncore.user.RegisteredUser;
import dev.frydae.commoncore.user.UserManager;

import java.util.List;
import java.util.Map;

public class Commands {
    public static void registerCommands() {
        registerContexts();
        registerCompletions();

        FabricCommandManager.upsertCommands();
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
}
