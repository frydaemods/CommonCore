package dev.frydae.beguild;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import lombok.SneakyThrows;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.lang.reflect.Type;

public class ConfigManager {
    @SafeVarargs
    @SneakyThrows(IOException.class)
    public static <T> T loadConfig(@NotNull String folderName, @NotNull String fileName, Type type, Pair<Class<?>, TypeAdapter<?>>... typeAdapters) {
        if (!fileName.endsWith(".json")) {
            fileName = fileName + ".json";
        }

        File configDir = FabricLoader.getInstance().getConfigDir().toFile();
        File accessManager = new File(configDir, folderName);
        File file = new File(accessManager, fileName);

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
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
            }
        }

        return null;
    }

    @SafeVarargs
    @SneakyThrows(IOException.class)
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

        File configDir = FabricLoader.getInstance().getConfigDir().toFile();
        File accessManager = new File(configDir, folderName);
        File fileToSave = new File(accessManager, fileName);

        if (!accessManager.exists()) {
            accessManager.mkdirs();
        }

        if (!fileToSave.exists()) {
            fileToSave.createNewFile();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
            writer.write(gson.toJson(toSave));
            writer.newLine();
        }
    }
}
