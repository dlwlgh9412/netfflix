package com.copago.netfflix.web.dto;

import com.copago.netfflix.entity.UserEntity;

public record UserResponse(
        String id,
        String userName
) {
    public UserResponse(UserEntity entity) {
        this(entity.getUserId(), entity.getUserName());
    }
}
