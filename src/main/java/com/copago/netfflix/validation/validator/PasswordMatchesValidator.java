package com.copago.netfflix.validation.validator;

import com.copago.netfflix.validation.annotation.PasswordMatches;
import com.copago.netfflix.web.dto.UserPasswordUpdateRequest;
import com.copago.netfflix.web.dto.UserRegisterRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value instanceof UserRegisterRequest request) {
            boolean result = request.password().equals(request.matchingPassword());
            if (!result) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("비밀번호가 일치하지 않습니다.").addPropertyNode("matchingPassword").addConstraintViolation();
            }
            return result;
        } else if (value instanceof UserPasswordUpdateRequest request) {
            boolean result = request.password().equals(request.matchingPassword());
            if (!result) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("비밀번호가 일치하지 않습니다.").addPropertyNode("matchingPassword").addConstraintViolation();
            }
            return result;
        }
        return false;
    }
}
