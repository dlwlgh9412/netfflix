package com.copago.netfflix.web.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public record UserRegisterRequest(
        @Email(message = "이메일 형식이 아닙니다.")
        @NotBlank(message = "이메일은 필수 입력 값 입니다.")
        String email,
        String password,
        @NotBlank(message = "이름은 필수 입력 값 입니다.")
        String userName
) {
}
