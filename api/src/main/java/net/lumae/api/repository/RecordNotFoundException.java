package net.lumae.api.repository;

public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(String id, String type) {
        super(String.format("ID %s not found in %s", id, type));
    }
}
