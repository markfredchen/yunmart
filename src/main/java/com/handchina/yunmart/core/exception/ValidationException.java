package com.handchina.yunmart.core.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by markfredchen on 3/26/15.
 */
public class ValidationException extends RuntimeException {

    private List<ValidationError> validationErrors = new ArrayList<ValidationError>();

    public ValidationException() {
        super();
    }

    public ValidationException(List<ValidationError> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public ValidationException(ValidationError validationError) {
        validationErrors.add(validationError);
    }

    public void setValidationErrors(List<ValidationError> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public List<ValidationError> getValidationErrors() {
        return validationErrors;
    }
}
