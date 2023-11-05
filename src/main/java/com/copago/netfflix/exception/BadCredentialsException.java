package com.copago.netfflix.exception;

import lombok.Getter;

@Getter
public class BadCredentialsException extends RuntimeException {
    public BadCredentialsException() {
        super("사용자 인증을 할 수 없습니다.");
    }

    public BadCredentialsException(String message) {
        super(message);
    }
}
