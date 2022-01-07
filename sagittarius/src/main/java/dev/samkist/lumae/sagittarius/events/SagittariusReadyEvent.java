package dev.samkist.lumae.sagittarius.events;

import dev.samkist.lumae.sagittarius.api.SagittariusApi;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class SagittariusReadyEvent extends Event {
    private static final HandlerList handlerList = new HandlerList();
    private SagittariusApi api;
    public SagittariusReadyEvent(SagittariusApi api) {
        this.api = api;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlerList;
    }

    public SagittariusApi getSagittariusApi() {
        return this.api;
    }

}
