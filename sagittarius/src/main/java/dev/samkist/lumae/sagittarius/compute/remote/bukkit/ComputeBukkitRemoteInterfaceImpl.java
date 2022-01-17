/**
 * File created by Samuel Pizette on 16 January 2022
 * https://github.com/Samkist/
 **/

package dev.samkist.lumae.sagittarius.compute.remote.bukkit;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.Consumer;

public class ComputeBukkitRemoteInterfaceImpl implements ComputeBukkitRemoteInterface {

    @Override
    public <T extends JavaPlugin> Consumer<T> processConsumer(String command, Class<T> pluginType) {
        return (plugin) -> {

        };
    }
}
