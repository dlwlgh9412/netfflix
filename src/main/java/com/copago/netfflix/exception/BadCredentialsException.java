package com.copago.netfflix.exception;

public class BadCredentialsException extends RuntimeException {
    public BadCredentialsException() {
        super("아이디와 패스워드를 확인해주세요.");
    }

    public BadCredentialsException(String message) {
        super(message);
    }
}
