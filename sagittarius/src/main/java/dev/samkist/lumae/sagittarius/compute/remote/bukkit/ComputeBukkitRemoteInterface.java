package dev.samkist.lumae.sagittarius.compute.remote.bukkit;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.Consumer;

public interface ComputeBukkitRemoteInterface {
    <T extends JavaPlugin> Consumer<T> processConsumer(String command, Class<T> pluginType);
}
