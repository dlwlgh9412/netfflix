package com.copago.netfflix.web.dto;

import com.copago.netfflix.entity.UserEntity;

import java.time.LocalDateTime;

public record UserUpdateResponse(
        Long id,
        LocalDateTime updatedAt
) {
    public UserUpdateResponse(UserEntity entity) {
        this(entity.getId(), entity.getUpdatedAt());
    }
}
