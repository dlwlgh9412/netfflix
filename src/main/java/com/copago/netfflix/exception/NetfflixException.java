package com.copago.netfflix.exception;

public class NetfflixException extends RuntimeException {
    public NetfflixException() {
    }

    public NetfflixException(String message) {
        super(message);
    }
}
