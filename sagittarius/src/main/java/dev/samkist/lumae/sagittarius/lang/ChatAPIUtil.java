/**
 * File created by Samuel Pizette
 * <p>
 * on 14 January 2022
 **/

package dev.samkist.lumae.sagittarius.lang;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;

public class ChatAPIUtil {

    /**
     * Converts a legacy message (one you would read in chat) to a json message.
     *
     * @param legacyString the legacy message to convert
     * @return the message converted to json
     */
    public static String legacyToJson(String legacyString) {
        if (legacyString == null) return "";
        return ComponentSerializer.toString(TextComponent.fromLegacyText(legacyString));
    }


    /**
     * Converts a json message to a legacy message.
     *
     * @param json the json message to convert
     * @return the legacy message
     */
    public static String jsonToLegacy(String json) {
        if (json == null) return "";
        return BaseComponent.toLegacyText(ComponentSerializer.parse(json));
    }



    public static String colorMessage(String legacyMsg) {
        return IridiumColorAPI.process(legacyMsg);
    }


}