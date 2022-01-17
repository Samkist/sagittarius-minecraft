package dev.samkist.lumae.sagittarius.command;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.Consumer;

public interface PluginOperation<P extends JavaPlugin> {
    Class<P> pluginType();

    Consumer<P> commandConsumer();
}
