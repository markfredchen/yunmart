package com.handchina.yunmart.core.exception;

/**
 * Created by markfredchen on 9/19/15.
 */
public class UnauthenticatedException extends RuntimeException {
    public UnauthenticatedException() {
        super("user.not.login");
    }
}
