package com.zahari.liveorderboard.error;

/**
 * Created by zahari on 26/03/2017.
 */
public class LiveOrderBookServiceException extends RuntimeException {
    public LiveOrderBookServiceException(String message) {
        super(message);
    }
}
