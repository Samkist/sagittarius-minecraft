package net.lumae.api.repository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(String id, String type) {
        super(String.format("ID %s not found in %s", id, type));
    }

    public RecordNotFoundException(String id, Class clazz) {
        super(String.format("Object %s of type %s", id, clazz.getName()));
    }
}
