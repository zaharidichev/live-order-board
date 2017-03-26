package com.zahari.liveorderboard.error;

/**
 * Created by zahari on 26/03/2017.
 */
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
