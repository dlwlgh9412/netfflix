package com.copago.netfflix.web.dto;

import java.io.Serializable;

public record TokenResponse(
        String type,
        String accessToken,
        Long expiresIn,
        Long refreshTokenExpiresIn
) implements Serializable {

}
