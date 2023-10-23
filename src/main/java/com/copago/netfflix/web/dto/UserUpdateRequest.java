package com.copago.netfflix.web.dto;

import jakarta.validation.constraints.NotBlank;

public record UserUpdateRequest(
        @NotBlank(message = "이름은 필수 입력 값 입니다.")
        String userName
) {
}
