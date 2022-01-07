package dev.samkist.lumae.sagittarius.data.models;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class NullBuilderException extends Exception {
    private final List<String> nullFields;

    public NullBuilderException(ModelBuilder builder, Class<? extends ModelBuilder> builderClass, String message) {
        super(message);

        nullFields = Arrays.asList(builderClass.getFields()).stream().filter(f -> {
            try {
                return Objects.isNull(f.get(builder));
            } catch (IllegalAccessException foo) {

            }
            return false;
        }).map(Field::getName).toList();
    }

    public List<String> getNullFields() {
        return nullFields;
    }
}
