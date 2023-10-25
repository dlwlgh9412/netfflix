package com.copago.netfflix.web.controller;

import com.copago.netfflix.dto.JwtToken;
import com.copago.netfflix.service.AuthService;
import com.copago.netfflix.web.dto.LoginRequest;
import com.copago.netfflix.web.util.WebAuthUtil;
import io.swagger.v3.oas.annotations.Operation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthRestController {

    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "로그인")
    public ResponseEntity<?> login(HttpServletRequest request, HttpServletResponse response, @Valid @RequestBody LoginRequest loginRequest) {
        JwtToken jwtToken = authService.generateToken(loginRequest);
        WebAuthUtil.setRefreshToken(response, request.getContextPath() + "/v1/auth/refresh", jwtToken.refreshToken().refreshToken());
        return new ResponseEntity<>(jwtToken.accessToken(), HttpStatus.OK);
    }

    @DeleteMapping("/logout")
    @Operation(summary = "로그아웃 (리프레시 토큰 삭제)")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        WebAuthUtil.deleteRefreshToken(response, request.getContextPath() + "/v1/auth/refresh");
        return ResponseEntity.ok().build();
    }
}