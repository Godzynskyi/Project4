package com.godzynskyi.exceptions;

/**
 * Throws when client enter not valid phone number.
 */
public class BadPhoneException extends Exception {
    public BadPhoneException(Throwable cause) {
        super(cause);
    }
}
