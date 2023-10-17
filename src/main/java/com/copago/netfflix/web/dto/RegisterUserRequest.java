package com.copago.netfflix.web.dto;

import com.copago.netfflix.validation.annotation.PasswordMatches;
import jakarta.validation.constraints.NotBlank;

@PasswordMatches
public record RegisterUserRequest(
        @NotBlank(message = "아이디는 필수 입력 값 입니다.")
        String id,
        String password,
        String matchingPassword
) {
}
