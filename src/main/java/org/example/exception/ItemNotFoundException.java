package org.example.exception;

public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException() {
    }

    public ItemNotFoundException(String message) {
        super(message);
    }
}
