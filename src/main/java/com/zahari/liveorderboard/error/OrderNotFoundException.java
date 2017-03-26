package com.zahari.liveorderboard.error;

/**
 * Created by zahari on 26/03/2017.
 */
public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
