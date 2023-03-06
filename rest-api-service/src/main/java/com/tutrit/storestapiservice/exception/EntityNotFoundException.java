package com.tutrit.storestapiservice.exception;

public class EntityNotFoundException extends RuntimeException{
    public final String entityName;

    public EntityNotFoundException(final String message, final String entityName) {
        super(message);
        this.entityName = entityName;
    }
}
