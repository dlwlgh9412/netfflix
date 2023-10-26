package com.copago.netfflix.web.dto;

import com.copago.netfflix.entity.UserEntity;

public record UserResponse(
        Long id,
        String email,
        String name
) {
    public UserResponse(UserEntity entity) {
        this(entity.getId(), entity.getEmail(), entity.getUserName());
    }
}
