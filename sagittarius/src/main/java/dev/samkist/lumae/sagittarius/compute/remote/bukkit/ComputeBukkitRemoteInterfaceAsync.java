package dev.samkist.lumae.sagittarius.compute.remote.bukkit;

import org.bukkit.plugin.java.JavaPlugin;
import org.redisson.api.RFuture;
import org.redisson.api.annotation.RRemoteAsync;

import java.util.function.Consumer;

@RRemoteAsync(ComputeBukkitRemoteInterface.class)
public interface ComputeBukkitRemoteInterfaceAsync {
    <T extends JavaPlugin> RFuture<Consumer<T>> processConsumer(String command, Class<T> pluginType);
}
