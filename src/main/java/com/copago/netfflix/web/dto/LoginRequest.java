package com.copago.netfflix.web.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public record LoginRequest(
        @NotBlank(message = "이메일은 필수 입력 값 입니다.")
        @Email(message = "이메일 형식이 아닙니다.")
        String email,
        @NotBlank(message = "비밀번호는 필수 입력 값 입니다.")
        String password
) implements Serializable {}