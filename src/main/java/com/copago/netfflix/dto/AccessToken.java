package com.copago.netfflix.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record AccessToken(
        @NotBlank(message = "액세스 토큰 값이 비어있습니다.")
        String token,
        @NotNull(message = "액세스 토큰 만료일 값이 비어있습니다.")
        Date accessTokenExpiration) {
}