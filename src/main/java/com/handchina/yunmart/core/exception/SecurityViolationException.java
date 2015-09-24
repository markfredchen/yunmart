package com.handchina.yunmart.core.exception;

/**
 * Created by markfredchen on 3/26/15.
 */
public class SecurityViolationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SecurityViolationException(Long userID, String right) {
        super("User[userID=" + userID + "] doesn't have Right[" + right + "]");
    }

    public SecurityViolationException() {
        super("Identity is not consistent");
    }

}
