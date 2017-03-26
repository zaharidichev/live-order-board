package com.zahari.liveorderboard.error;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by zahari on 26/03/2017.
 */
public class ServerError {
    @JsonProperty("error")
    private final String error;
    @JsonProperty("message")
    private final String message;

    public ServerError(String message, String error) {
        this.message = message;
        this.error = error;
    }

    public String getError() {
        return this.error;
    }

    public String getMessage() {
        return this.message;
    }

}