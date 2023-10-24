package com.copago.netfflix.web.dto;

import com.copago.netfflix.validation.annotation.PasswordMatches;

import javax.validation.constraints.NotBlank;

@PasswordMatches
public record UserRegisterRequest(
        @NotBlank(message = "아이디는 필수 입력 값 입니다.")
        String id,
        String password,
        String matchingPassword,
        @NotBlank(message = "이름은 필수 입력 값 입니다.")
        String userName
) {
}
