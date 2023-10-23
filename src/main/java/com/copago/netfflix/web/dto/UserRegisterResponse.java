package com.copago.netfflix.web.dto;

import java.time.LocalDateTime;

public record UserRegisterResponse(
        String id,
        LocalDateTime createdAt
) {
}
