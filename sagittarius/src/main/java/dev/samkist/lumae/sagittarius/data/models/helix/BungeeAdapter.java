package dev.samkist.lumae.sagittarius.data.models.helix;

import dev.samkist.lumae.sagittarius.lang.ChatAPIUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.bungeecord.BungeeComponentSerializer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import net.md_5.bungee.chat.ComponentSerializer;

import java.util.Objects;
import java.util.Optional;

public class BungeeAdapter {
    public static TextComponent helixToBungee(HelixComponent helix) {
        return new TextComponent(BungeeComponentSerializer.get().serialize(AdventureAdapter.helixToAdventure(helix)));
    }

    public static HelixComponent bungeeToHelix(TextComponent component) {
        HelixComponent helix = new HelixComponent();
        Component comp = BungeeComponentSerializer.get().deserialize(component.getExtra().toArray(new BaseComponent[0]));

        if(Objects.nonNull(component.getClickEvent())) {
            ClickEvent click = component.getClickEvent();
            ClickEvent.Action action = click.getAction();
            String value = click.getValue();
            helix.clickContext(new HelixClickContext(action, value));
        }

        if(Objects.nonNull(component.getHoverEvent())) {
            HoverEvent hover = component.getHoverEvent();
            HoverEvent.Action action = hover.getAction();
            switch(action) {
                case SHOW_TEXT -> {
                    Optional<Text> hoverText = hover
                            .getContents()
                            .stream()
                            .filter(c -> c instanceof Text)
                            .map(c -> (Text) c)
                            .findFirst();
                    hoverText.ifPresent(text -> {
                        if(text.getValue() instanceof String) {
                            String str = (String) text.getValue();
                            helix.hoverContext(new HelixHoverContext(HoverEvent.Action.SHOW_TEXT, str));
                        } else {
                            BaseComponent[] components = (BaseComponent[]) text.getValue();
                            String str = BaseComponent.toLegacyText(components);
                            helix.hoverContext(new HelixHoverContext(HoverEvent.Action.SHOW_TEXT, str));
                        }
                    });
                }
                default -> {}
            }
        }

        return helix;
    }

    public static String bungeeToLegacy(TextComponent component) {
        return ChatAPIUtil.jsonToLegacy(ComponentSerializer.toString(component));
    }
}
