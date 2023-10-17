package com.copago.netfflix.exception;

public class BadCredentialsException extends RuntimeException {
    public BadCredentialsException() {
        super("사용자 정보를 찾을 수 없습니다.");
    }

    public BadCredentialsException(String message) {
        super(message);
    }
}
