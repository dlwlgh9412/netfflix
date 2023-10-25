package com.copago.netfflix.web.dto;

import com.copago.netfflix.entity.UserEntity;

import java.time.LocalDateTime;

public record UserRegisterResponse(
        Long id,
        LocalDateTime createdAt
) {
    public UserRegisterResponse(UserEntity entity) {
        this(entity.getId(), entity.getCreatedAt());
    }
}
