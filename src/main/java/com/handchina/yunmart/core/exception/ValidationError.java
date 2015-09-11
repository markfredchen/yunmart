package com.handchina.yunmart.core.exception;

import org.springframework.validation.FieldError;

/**
 * Created by markfredchen on 3/26/15.
 */
public class ValidationError {

    private final String attributeName;
    private final String message;

    public ValidationError(final String attributeName, final String message) {
        this.attributeName = attributeName;
        this.message = message;
    }

    public ValidationError(FieldError err) {
        attributeName = err.getField();
        message = err.getDefaultMessage();
    }

    public String getExternalPropertyName() {
        return attributeName;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return attributeName + " : " + message;
    }
}
