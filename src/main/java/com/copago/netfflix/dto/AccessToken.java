package com.copago.netfflix.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public record AccessToken(
        @NotBlank(message = "액세스 토큰 값이 비어있습니다.")
        String accessToken,
        @NotNull(message = "액세스 토큰 만료일 값이 비어있습니다.")
        Date accessTokenExpiration) {
}