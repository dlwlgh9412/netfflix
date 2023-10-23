package com.copago.netfflix.web.dto;

import java.time.LocalDateTime;

public record UserUpdateResponse(
        String id,
        LocalDateTime updatedAt
) {
}
