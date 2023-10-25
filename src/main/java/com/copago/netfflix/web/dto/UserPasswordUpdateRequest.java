package com.copago.netfflix.web.dto;

import javax.validation.constraints.NotBlank;

public record UserPasswordUpdateRequest(
        @NotBlank(message = "패스워드는 필수 입력 값 입니다.")
        String password
) {
}