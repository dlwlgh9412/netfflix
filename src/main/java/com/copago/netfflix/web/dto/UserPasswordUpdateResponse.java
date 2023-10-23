package com.copago.netfflix.web.dto;

import java.time.LocalDateTime;

public record UserPasswordUpdateResponse(
        String id,
        LocalDateTime updatedAt
) {
}
