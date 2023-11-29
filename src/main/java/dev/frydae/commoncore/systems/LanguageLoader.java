package dev.frydae.commoncore.systems;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dev.frydae.commoncore.ConfigManager;
import lombok.SneakyThrows;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class LanguageLoader {
    private static final Map<String, String> languageCache = Maps.newConcurrentMap();

    @Nullable
    public static String getString(String key, Object... replacements) {
        String line = languageCache.getOrDefault(key, null);

        if (replacements != null) {
            line = String.format(line, replacements);
        }

        return line;
    }

    @SneakyThrows(IOException.class)
    public static void loadFile(String fileName) {
        Map<String, String> loadedMap = ConfigManager.loadConfig("Language", fileName, new TypeToken<Map<String, String>>(){}.getType());

        if (loadedMap != null) {
            languageCache.putAll(loadedMap);
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(LanguageLoader.class.getClassLoader().getResourceAsStream("assets/language/" + fileName))
                )
        )) {
            Map<String, String> loaded = new Gson().fromJson(reader, new TypeToken<Map<String, String>>(){}.getType());

            loaded.forEach((key, value) -> {
                if (!languageCache.containsKey(key)) {
                    System.out.println("adding new key: " + key);
                    languageCache.put(key, value);
                }
            });
        }

        languageCache.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

        ConfigManager.saveConfig("Language", fileName, languageCache);
    }
}
