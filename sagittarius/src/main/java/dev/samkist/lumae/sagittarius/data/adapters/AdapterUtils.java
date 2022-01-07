package dev.samkist.lumae.sagittarius.data.adapters;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class AdapterUtils {
    private AdapterUtils() {

    }

    public static void throwIfNotObject(JsonElement json) throws JsonParseException {
        if(!json.isJsonObject()) throw new JsonParseException("Not a JSON Object");
    }
}
