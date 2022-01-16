/**
 * File created by Samuel Pizette
 * <p>
 * on 14 January 2022
 **/

package dev.samkist.lumae.sagittarius.data.models.helix;

import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.util.Objects;

public class AdventureAdapter {
    public static TextComponent helixToAdventure(HelixComponent helix) {
        TextComponent message = legacyToAdventure(helix.legacyText());
        if(Objects.nonNull(helix.clickContext())) {
            HelixClickContext click = helix.clickContext();
            String clickValue = click.value();
            message.clickEvent(
                    switch(click.action()) {
                        case OPEN_URL -> ClickEvent.openUrl(clickValue);
                        case RUN_COMMAND -> ClickEvent.runCommand(clickValue);
                        case SUGGEST_COMMAND -> ClickEvent.suggestCommand(clickValue);
                        case COPY_TO_CLIPBOARD -> ClickEvent.copyToClipboard(clickValue);
                        default -> null;
                    }
            );
        }

        if(Objects.nonNull(helix.hoverContext())) {
            message.hoverEvent(
                    switch(helix.hoverContext().action()) {
                        case SHOW_TEXT ->
                            HoverEvent.showText(
                                    legacyToAdventure(
                                            helix.clickContext().value()
                                    )
                            );
                        default -> null;
                    });
        }
        return message;
    }

    public static TextComponent legacyToAdventure(String legacy) {
        return LegacyComponentSerializer
                .legacyAmpersand()
                .deserialize(legacy);
    }

    public static String helixToAdventureJson(HelixComponent helix) {
        return GsonComponentSerializer.gson().serialize(helixToAdventure(helix));
    }
}
