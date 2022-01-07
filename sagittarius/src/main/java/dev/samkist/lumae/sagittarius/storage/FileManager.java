package dev.samkist.lumae.sagittarius.storage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dev.samkist.lumae.sagittarius.Sagittarius;
import dev.samkist.lumae.sagittarius.data.models.ChatFormat;
import dev.samkist.lumae.sagittarius.data.models.JoinLeaveFormat;
import dev.samkist.lumae.sagittarius.data.models.MilkyPlayer;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class FileManager {

    private final Sagittarius plugin;
    private final File dataFolder;
    private final Path dataPath;
    private final String dataString;
    private final Map<String, YamlConfiguration> configFiles = new HashMap<>();
    private final Map<String, Path> paths = new HashMap<>();
    private final ArrayList<String> ymlFiles = new ArrayList<>(List.of("config.yml"));
    private final ArrayList<String> jsonFiles = new ArrayList<>();
    private final List<String> directories = Arrays.asList("data");

    public FileManager(Sagittarius sagittarius) {
        this.plugin = sagittarius;
        this.dataFolder = sagittarius.getDataFolder();
        this.dataPath = dataFolder.toPath();
        this.dataString = dataPath.toString();
        registerJsonFile(ChatFormat.scope, "data");
        registerJsonFile(JoinLeaveFormat.scope, "data");
        registerJsonFile(MilkyPlayer.scope, "data");
    }

    public void load() {
        if(!dataFolder.exists()) {
            dataFolder.mkdir();
        }

        directories.forEach(directory -> {
            Path folder = Paths.get(dataString, directory);
            if(Files.notExists(folder)) {
                try {
                    Files.createDirectories(dataPath.resolve(directory));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        loadYaml(ymlFiles);
        loadJson(jsonFiles);
    }

    public Path pathByScope(String scope) {
        return paths.get(scope);
    }

    public void reloadConfigurations() {
        ymlFiles.forEach(ymlFile -> {
            try {
                configFiles.get(ymlFile).load(new File(dataFolder, ymlFile));
            } catch (Exception e) {
            }
        });
    }

    public void loadYaml(List<String> ymlFiles) {
        ymlFiles.forEach(this::loadYaml);
    }

    public void loadYaml(String ymlFile) {
        Path path = Paths.get(dataString, ymlFile);

        generateResource(ymlFile, path);

        configFiles.put(ymlFile, YamlConfiguration.loadConfiguration(new File(dataFolder, ymlFile)));
    }

    public void loadJson(List<String> jsonFiles) {
        jsonFiles.forEach(this::loadJson);
    }

    public void loadJson(String jsonFile) {
        String json = "data/" + jsonFile;
        Path path = Paths.get(dataPath.resolve(json).toUri());
        generateResource(json, path);
    }

    public void generateResource(String treeName, Path path) {
        if (Files.notExists(path)) {
            try {
                Files.copy(getClass().getClassLoader().getResourceAsStream(treeName), path);
            } catch (IOException e) {
                generateFile(treeName, path);
            }
        }
    }

    public void generateFile(String treeName, Path path) {
        String space = " ";
        InputStream inputStream = new ByteArrayInputStream(space.getBytes(Charset.forName("UTF-8")));
        try {
            Files.copy(inputStream, path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void registerJsonFile(String name, String folder) {
        String jsonName = name + ".json";
        paths.put(name, dataPath.resolve(folder).resolve(jsonName));
        jsonFiles.add(jsonName);
    }

    public <K, V> HashMap<K, V> loadMap(Gson gson, String scope,  TypeToken<? extends Map<K, V>> typeToken) {
        return loadMap(gson, pathByScope(scope), typeToken);
    }

    // Loads a map from a json file
    private <K, V> HashMap<K, V> loadMap(Gson gson, Path file, TypeToken<? extends Map<K, V>> typeToken) {
        final HashMap<K, V> map = new HashMap<>();

        try (BufferedReader reader = Files.newBufferedReader(file)) {
            map.putAll(gson.fromJson(reader, typeToken.getType()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }

    public <K, V> void saveMap(Gson gson, String scope, Map<K, V> map) {
        saveMap(gson, pathByScope(scope), map);
    }

    // Saves a map to a json file
    private <K, V> void saveMap(Gson gson, Path file, Map<K, V> map) {
        if(Objects.isNull(map)) {
            return;
        }

        try (Writer writer = new FileWriter(new File(file.toUri()))) {
            gson.toJson(map, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T> Optional<T> loadObject(Gson gson, String scope, Type type) {
        return loadObject(gson, pathByScope(scope), type);
    }

    // Loads an object from JSON assuming it has a type adapter
    private <T> Optional<T> loadObject(Gson gson, Path file, Type type) {
        // Satisfy compiler :)
        T object = null;
        try {
            object = gson.fromJson(new FileReader(file.toFile()), type);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(object);
    }

    public void saveObject(Gson gson, String scope, Object object) {
        saveObject(gson, pathByScope(scope), object);
    }

    private void saveObject(Gson gson, Path file, Object object) {
        if(Objects.isNull(object)) {
            return;
        }

        try (Writer writer = new FileWriter(new File(file.toUri()))) {
            gson.toJson(object, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
