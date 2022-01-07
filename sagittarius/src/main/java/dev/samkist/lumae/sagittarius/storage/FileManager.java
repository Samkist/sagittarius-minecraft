package dev.samkist.lumae.sagittarius.storage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class FileManager {

    private final File dataFolder;
    private final Path dataPath;
    private final String dataString;
    private final Map<String, YamlConfiguration> configFiles = new HashMap<>();
    private final Map<String, Path> paths = new HashMap<>();
    private final ArrayList<String> ymlFiles = new ArrayList<>(List.of("config.yml"));
    private final ArrayList<String> jsonFiles = new ArrayList<>();
    private final List<String> directories = Arrays.asList("data");

    public FileManager(File dataFolder) {
        this.dataFolder = dataFolder;
        this.dataPath = dataFolder.toPath();
        this.dataString = dataPath.toString();
        registerJsonFile("chat-formats", "data");
        registerJsonFile("join-leave-formats", "data");
        registerJsonFile("milky-players", "data");
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

        generateFile(ymlFile, path);

        configFiles.put(ymlFile, YamlConfiguration.loadConfiguration(new File(dataFolder, ymlFile)));
    }

    public void loadJsonFiles(List<String> jsonFiles) {
        jsonFiles.forEach(this::loadJsonFile);
    }

    public void loadJsonFile(String jsonFile) {
        String json = "data/" + jsonFile;
        Path path = Paths.get(dataPath.resolve(json).toUri());
        generateFile(json, path);
    }

    public void generateFile(String treeName, Path path) {
        if (Files.notExists(path)) {
            try {
                Files.copy(getClass().getClassLoader().getResourceAsStream(treeName), path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void registerJsonFile(String name, String folder) {
        String jsonName = name + ".json";
        paths.put(name, dataPath.resolve(folder).resolve(jsonName));
        jsonFiles.add(jsonName);
    }

    // Loads a map from a json file
    public <K, V> HashMap<K, V> loadMap(Gson gson, Path file, TypeToken<? extends Map<K, V>> typeToken) {
        final HashMap<K, V> map = new HashMap<>();

        try (BufferedReader reader = Files.newBufferedReader(file)) {
            map.putAll(gson.fromJson(reader, typeToken.getType()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }

    // Saves a map to a json file
    public <K, V> void saveMap(Gson gson, Path file, Map<K, V> map) {
        if(Objects.isNull(map)) {
            return;
        }

        try (Writer writer = new FileWriter(new File(file.toUri()))) {
            gson.toJson(map, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Loads an object from JSON assuming it has a type adapter
    public <T> Optional<T> loadObject(Gson gson, Path file, Type type) {
        // Satisfy compiler :)
        T object = null;
        try {
            object = gson.fromJson(new FileReader(file.toFile()), type);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(object);
    }

    public void saveObject(Gson gson, Path file, Object object) {
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
