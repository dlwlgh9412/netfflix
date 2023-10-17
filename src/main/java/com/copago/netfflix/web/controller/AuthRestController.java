package com.copago.netfflix.web.controller;

import com.copago.netfflix.dto.JwtToken;
import com.copago.netfflix.service.AuthService;
import com.copago.netfflix.web.dto.TokenRequest;
import com.copago.netfflix.web.util.WebAuthUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthRestController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletRequest request, HttpServletResponse response, @Valid @RequestBody TokenRequest tokenRequest) {
        JwtToken jwtToken = authService.generateToken(tokenRequest);
        WebAuthUtil.setRefreshToken(response, request.getContextPath() + "/v1/auth/refresh", jwtToken.refreshToken().token());
        return null;
    }

    @DeleteMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        WebAuthUtil.deleteRefreshToken(response, request.getContextPath() + "/v1/auth/refresh");
        return ResponseEntity.ok().build();
    }
}