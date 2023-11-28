package dev.frydae.commoncore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.lang.reflect.Type;

public class ConfigManager {
    @SneakyThrows(IOException.class)
    public static <T> T loadConfig(@NotNull String folderName, @NotNull String fileName, Type type) {
        if (!fileName.endsWith(".json")) {
            fileName = fileName + ".json";
        }

        File configDir = FabricLoader.getInstance().getConfigDir().toFile();
        File accessManager = new File(configDir, folderName);
        File file = new File(accessManager, fileName);

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                Gson gson = new GsonBuilder().setPrettyPrinting()
                        .excludeFieldsWithoutExposeAnnotation()
                        .create();

                return gson.fromJson(reader, type);
            }
        }

        return null;
    }

    @SneakyThrows(IOException.class)
    public static void saveConfig(@NotNull String folderName, @NotNull String fileName, @NotNull Object toSave) {
        if (!fileName.endsWith(".json")) {
            fileName = fileName + ".json";
        }

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.excludeFieldsWithoutExposeAnnotation();
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
