package com.copago.netfflix.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public record RefreshToken(
        @NotBlank(message = "리프레시 토큰 값이 비어있습니다.")
        String token,
        @NotNull(message = "리프레시 토큰 만료일 값이 비어있습니다.")
        Date refreshTokenExpiration) {
}
