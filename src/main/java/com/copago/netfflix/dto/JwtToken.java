package com.copago.netfflix.dto;


import javax.validation.constraints.NotNull;

public record JwtToken(
        @NotNull(message = "액세스 토큰이 비어있습니다.")
        AccessToken accessToken,
        @NotNull(message = "리프레시 토큰이 비어있습니다.")
        RefreshToken refreshToken) {
}

