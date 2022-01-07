package dev.samkist.lumae.sagittarius.data.adapters;

import com.google.gson.*;
import dev.samkist.lumae.sagittarius.data.models.ChatFormat;

import java.lang.reflect.Type;

import static dev.samkist.lumae.sagittarius.data.adapters.AdapterUtils.throwIfNotObject;

public class ChatFormatAdapter implements JsonDeserializer<ChatFormat>, JsonSerializer<ChatFormat> {
    @Override
    public ChatFormat deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        throwIfNotObject(json);

        return null;
    }

    @Override
    public JsonElement serialize(ChatFormat src, Type typeOfSrc, JsonSerializationContext context) {
        return null;
    }
}
