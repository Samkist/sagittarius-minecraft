package dev.samkist.lumae.sagittarius.data.models.helix;

import net.md_5.bungee.api.chat.HoverEvent;

public record HelixHoverContext(HoverEvent.Action action, String value) {
}
