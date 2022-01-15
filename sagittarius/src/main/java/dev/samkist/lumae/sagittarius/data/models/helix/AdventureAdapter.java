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
            message.clickEvent(
                    ClickEvent.openUrl(
                            helix.clickContext().value()
                    )
            );
        }

        if(Objects.nonNull(helix.runCommandContext())) {
            message.clickEvent(
                    ClickEvent.runCommand(
                            helix.runCommandContext().value()
                    )
            );
        }

        if(Objects.nonNull(helix.suggestCommandContext())) {
            message.clickEvent(
                    ClickEvent.suggestCommand(
                            helix.suggestCommandContext().value()
                    )
            );
        }

        if(Objects.nonNull(helix.copyContext())) {
            message.clickEvent(
                    ClickEvent.copyToClipboard(
                            helix.copyContext().value()
                    )
            );
        }

        if(Objects.nonNull(helix.hoverContext())) {
            message.hoverEvent(
                    HoverEvent.showText(
                            legacyToAdventure(
                                    helix.clickContext().value()
                            )
                    ));
        }

        if(Objects.nonNull(helix.showItemContext())) {
            //TODO
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
