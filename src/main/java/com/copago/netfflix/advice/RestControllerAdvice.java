package com.copago.netfflix.advice;

import com.copago.netfflix.enums.ErrorCode;
import com.copago.netfflix.exception.BadCredentialsException;
import com.copago.netfflix.exception.UserNotFoundException;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.rmi.server.ExportException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class RestControllerAdvice {
    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> bindException(BindException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getBindingResult()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getBindingResult()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> userNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponse(ErrorCode.BAD_REQUEST, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> badCredentialsException(BadCredentialsException ex) {
        return new ResponseEntity<>(new ErrorResponse(ErrorCode.UNAUTHORIZED, ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> illegalArgumentException(IllegalArgumentException illegalArgumentException) {
        log.error("IllegalArgumentException: {}", illegalArgumentException.getMessage());
        return new ResponseEntity<>(new ErrorResponse(ErrorCode.BAD_REQUEST, "잘못된 요청 값 입니다."), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExportException.class)
    public ResponseEntity<?> exportException(ExportException ex) {
        log.error("Jwt parse error -> exportException: {}", ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse(ErrorCode.BAD_REQUEST, "토큰 변환 중 오류가 발생 하였습니다."), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnsupportedJwtException.class)
    public ResponseEntity<?> unsupportedJwtException(UnsupportedJwtException ex) {
        log.error("Jwt parse error -> UnsupportedJwtException: {}", ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse(ErrorCode.BAD_REQUEST, "지원 하지 않는 형식의 토큰 입니다."), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<?> signatureException(SignatureException ex) {
        log.error("Jwt parse error -> SignatureException: {}", ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse(ErrorCode.BAD_REQUEST, "지원 하지 않는 형식의 토큰 입니다."), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<?> malformedJwtException(MalformedJwtException ex) {
        log.error("Jwt parse error -> signatureException: {}", ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse(ErrorCode.BAD_REQUEST, "올바르지 않은 구성 형식의 토큰 입니다."), HttpStatus.BAD_REQUEST);
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private static class ErrorResponse {
        private ErrorCode errorCode;
        private String messages;
        private Map<String, String> errors;

        public ErrorResponse(ErrorCode errorCode, String messages) {
            this.errorCode = errorCode;
            this.messages = messages;
        }

        public ErrorResponse(BindingResult bindingResult) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });

            this.errorCode = ErrorCode.BAD_REQUEST;
            this.errors = errors;
        }
    }
}
