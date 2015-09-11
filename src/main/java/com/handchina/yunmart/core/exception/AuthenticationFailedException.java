package com.handchina.yunmart.core.exception;

/**
 * Created by markfredchen on 3/26/15.
 */
public class AuthenticationFailedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AuthenticationFailedException(String username) {
        super("User[username=" + username + "] fails to authenticate");
    }

    public AuthenticationFailedException(Long userID, String username) {
        super("User[userID=" + userID + ", username=" + username + "] fails to authenticate");
    }

}
