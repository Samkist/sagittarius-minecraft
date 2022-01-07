package dev.samkist.lumae.sagittarius.data.adapters;

import com.google.gson.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class LocationAdapter implements JsonDeserializer<Location>, JsonSerializer<Location> {


    @Override
    public Location deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if ( !json.isJsonObject() ) {
            throw new JsonParseException( "not a JSON object" );
        }

        HashMap<String, JsonElement> elements = new HashMap<>();

        final JsonObject obj = (JsonObject) json;

        List<String> attributes = Arrays.asList("world", "x", "y", "z", "yaw", "pitch");
        attributes.forEach(a -> elements.put(a, obj.get(a)));

        List<String> nullElements = elements.entrySet().stream().filter(e -> Objects.isNull(e.getValue())).map(e -> e.getKey()).collect(Collectors.toList());

        if (nullElements.size() > 0) {
            StringBuilder builder = new StringBuilder();
            nullElements.forEach(e -> builder.append(e).append(' '));
            builder.append("were null");
            throw new JsonParseException(builder.toString());
        }

        Map<String, JsonPrimitive> primitives = elements.entrySet().stream().filter(e -> e.getValue().isJsonPrimitive()).collect(Collectors.toMap(Map.Entry::getKey, e -> (JsonPrimitive)e.getValue()));
        List<String> complexList = elements.entrySet().stream().filter(e -> !e.getValue().isJsonPrimitive()).map(Map.Entry::getKey).collect(Collectors.toList());
        if(complexList.size() > 0) {
            StringBuilder builder = new StringBuilder();
            complexList.forEach(e -> builder.append(e).append(' '));
            builder.append("were not primitives");
            throw new JsonParseException(builder.toString());
        }

        JsonPrimitive world = primitives.get("world");
        if (!world.isString()) {
            throw new JsonParseException( "world is not a string" );
        }

        World worldInstance = Bukkit.getWorld( world.getAsString() );

        return new Location(
                worldInstance,
                elements.get("x").getAsDouble(),
                elements.get("y").getAsDouble(),
                elements.get("z").getAsDouble(),
                elements.get("yaw").getAsFloat(),
                elements.get("pitch").getAsFloat() );

    }

    @Override
    public JsonElement serialize(Location location, Type typeOfSrc, JsonSerializationContext context) {
        final JsonObject obj = new JsonObject();
        obj.addProperty( "world", location.getWorld().getName() );
        obj.addProperty( "x", location.getX() );
        obj.addProperty( "y", location.getY() );
        obj.addProperty( "z", location.getZ() );
        obj.addProperty( "yaw", location.getYaw() );
        obj.addProperty( "pitch", location.getPitch() );
        return obj;
    }
}
