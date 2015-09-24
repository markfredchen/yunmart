package com.handchina.yunmart.web.exception;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;

public enum ExceptionErrorCode {

    VALIDATION_ERROR(HttpStatus.BAD_REQUEST),

    OBJECT_NOT_FOUND(HttpStatus.NOT_FOUND),

    UNAUTHENTICATED(HttpStatus.FORBIDDEN),

    SECURITY_VIOLATION(HttpStatus.FORBIDDEN),

    AUTHENTICATION_FAILED(HttpStatus.FORBIDDEN),

    SYSTEM_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR);

    HttpStatus httpStatus;

    ExceptionErrorCode(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
