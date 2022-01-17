package net.lumae.api.repository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RecordNotFoundException extends dev.samkist.lumae.sagittarius.exceptions.RecordNotFoundException {

    public RecordNotFoundException(String id, String type) {
        super(id, type);
    }

    public RecordNotFoundException(String id, Class clazz) {
        super(id, clazz);
    }
}
