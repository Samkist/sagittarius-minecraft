package dev.samkist.lumae.sagittarius.command;

import dev.samkist.lumae.sagittarius.data.Identifiable;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.Consumer;

public interface Command<C extends Command, P extends JavaPlugin> extends Identifiable<C> {
    Class<C> commandType();

    Class<P> pluginType();
}
