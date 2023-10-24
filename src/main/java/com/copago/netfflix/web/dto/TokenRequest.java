package com.copago.netfflix.web.dto;


import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public record TokenRequest(
        @NotBlank(message = "아이디는 필수 입력 값 입니다.")
        String id,
        @NotBlank(message = "비밀번호는 필수 입력 값 입니다.")
        String password
) implements Serializable {}