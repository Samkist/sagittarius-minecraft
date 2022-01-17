/**
 * File created by Samuel Pizette on 16 January 2022
 * https://github.com/Samkist/
 **/

package dev.samkist.lumae.sagittarius.exceptions;

public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(String id, String type) {
        super(String.format("ID %s not found in %s", id, type));
    }

    public RecordNotFoundException(String id, Class clazz) {
        super(String.format("Object %s of type %s", id, clazz.getName()));
    }
}
