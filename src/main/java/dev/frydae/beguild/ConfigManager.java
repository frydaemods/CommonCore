package dev.frydae.beguild;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigManager {
    @SafeVarargs
    public static <T> T loadConfig(@NotNull String folderName, @NotNull String fileName, Type type, Pair<Class<?>, TypeAdapter<?>>... typeAdapters) {
        if (!fileName.endsWith(".json")) {
            fileName = fileName + ".json";
        }

        Path configDir = FabricLoader.getInstance().getConfigDir();
        Path folderDir = configDir.resolve(folderName);
        Path fileToLoad = folderDir.resolve(fileName);

        if (Files.exists(fileToLoad)) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileToLoad.toString()))) {
                GsonBuilder builder = new GsonBuilder();

                if (typeAdapters != null) {
                    for (Pair<Class<?>, TypeAdapter<?>> typeAdapter : typeAdapters) {
                        builder.registerTypeAdapter(typeAdapter.getKey(), typeAdapter.getValue());
                    }
                }

                Gson gson = builder.setPrettyPrinting()
                        .excludeFieldsWithoutExposeAnnotation()
                        .create();

                return gson.fromJson(reader, type);
            } catch (IOException e) {
                BeGuildCommon.getLogger().error("Could not load config file " + fileName, e);
            }
        }

        return null;
    }

    @SafeVarargs
    public static void saveConfig(@NotNull String folderName, @NotNull String fileName, @NotNull Object toSave, Pair<Class<?>, TypeAdapter<?>>... typeAdapters) {
        if (!fileName.endsWith(".json")) {
            fileName = fileName + ".json";
        }

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.excludeFieldsWithoutExposeAnnotation();

        if (typeAdapters != null) {
            for (Pair<Class<?>, TypeAdapter<?>> typeAdapter : typeAdapters) {
                builder.registerTypeAdapter(typeAdapter.getKey(), typeAdapter.getValue());
            }
        }

        Gson gson = builder.create();

        Path configDir = FabricLoader.getInstance().getConfigDir();
        Path folderDir = configDir.resolve(folderName);
        Path fileToSave = folderDir.resolve(fileName);

        try {
            Files.createDirectories(folderDir);

            if (!Files.exists(fileToSave)) {
                Files.createFile(fileToSave);
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave.toString()))) {
                writer.write(gson.toJson(toSave));
                writer.newLine();
            }
        } catch (IOException e) {
            BeGuildCommon.getLogger().error("Could not save config file " + fileName, e);
        }
    }
}
