package dev.samkist.lumae.sagittarius.data.models.helix;

import net.md_5.bungee.api.chat.ClickEvent;

public record HelixClickContext(ClickEvent.Action action, String value) {

}
