package com.copago.netfflix.web.dto;

import com.copago.netfflix.validation.annotation.PasswordMatches;

@PasswordMatches
public record UserPasswordUpdateRequest(
        String password,
        String matchingPassword
) {
}