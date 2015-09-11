package com.handchina.yunmart.web.rest.controller;

import com.handchina.yunmart.core.exception.ObjectNotFoundException;
import com.handchina.yunmart.core.exception.ValidationError;
import com.handchina.yunmart.core.exception.ValidationException;
import com.handchina.yunmart.web.exception.ExceptionDetail;
import com.handchina.yunmart.web.exception.ExceptionErrorCode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by markfredchen on 3/8/15.
 */
public class BaseController {
    private static final Log log = LogFactory.getLog(BaseController.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDetail> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e);
        ExceptionDetail detail = new ExceptionDetail();
        detail.setErrorCode(ExceptionErrorCode.VALIDATION_ERROR);
        List<ValidationError> validationErrors = new ArrayList<>();
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        validationErrors.addAll(errors.stream().map(error -> new ValidationError((FieldError) error)).collect(Collectors.toList()));
        detail.setValidationErrors(validationErrors);
        return new ResponseEntity<>(detail, ExceptionErrorCode.VALIDATION_ERROR.getHttpStatus());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ExceptionDetail> handleValidationException(ValidationException e) {
        log.error(e);
        ExceptionDetail detail = new ExceptionDetail();
        detail.setErrorCode(ExceptionErrorCode.VALIDATION_ERROR);
        detail.setValidationErrors(e.getValidationErrors());
        return new ResponseEntity<>(detail, ExceptionErrorCode.VALIDATION_ERROR.getHttpStatus());
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ExceptionDetail> handleObjectNotFound(ObjectNotFoundException e) {
        log.error(e);
        ExceptionDetail detail = new ExceptionDetail();
        detail.setErrorCode(ExceptionErrorCode.OBJECT_NOT_FOUND);
        detail.setMessage(e.getMessage());
        return new ResponseEntity<>(detail, ExceptionErrorCode.OBJECT_NOT_FOUND.getHttpStatus());
    }


    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ExceptionDetail> handleException(Throwable e) {
        log.error(e);
        ExceptionDetail detail = new ExceptionDetail();
        detail.setErrorCode(ExceptionErrorCode.SYSTEM_EXCEPTION);
        detail.setMessage(e.getMessage());
        return new ResponseEntity<>(detail, ExceptionErrorCode.SYSTEM_EXCEPTION.getHttpStatus());
    }
}

