package com.copago.netfflix.advice;

import com.copago.netfflix.enums.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    private static class ErrorResponse {
        private ErrorCode errorCode;
        private List<String> messages;
        private Map<String, String> fieldErrors;

        public ErrorResponse(ErrorCode errorCode, List<String> messages) {
            this.errorCode = errorCode;
            this.messages = messages;
        }

        public ErrorResponse(BindingResult bindingResult) {
            Map<String, String> fieldErrors = new HashMap<>();
            bindingResult.getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                fieldErrors.put(fieldName, errorMessage);
            });
            this.fieldErrors = fieldErrors;
        }

        public ErrorResponse(Map<String, String> fieldErrors) {
            this.fieldErrors = fieldErrors;
        }
    }
}
