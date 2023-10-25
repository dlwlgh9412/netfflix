package com.copago.netfflix.service;

import com.copago.netfflix.dto.AccessToken;
import com.copago.netfflix.dto.JwtToken;
import com.copago.netfflix.dto.RefreshToken;
import com.copago.netfflix.entity.RefreshTokenEntity;
import com.copago.netfflix.entity.UserEntity;
import com.copago.netfflix.exception.BadCredentialsException;
import com.copago.netfflix.repository.RefreshTokenRepository;
import com.copago.netfflix.util.JwtProvider;
import com.copago.netfflix.web.dto.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final RefreshTokenRepository tokenRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional
    public JwtToken generateToken(LoginRequest request) {
        UserEntity user = userService.loadUserByEmail(request.email());
        if (!passwordEncoder.matches(request.password(), user.getPassword())) throw new BadCredentialsException();

        AccessToken accessToken = jwtProvider.generateAccessToken(user);
        RefreshTokenEntity entity = tokenRepository.findByUserId(user.getId()).orElseGet(() -> {
            RefreshToken refreshToken = jwtProvider.generateRefreshToken(user);
            return tokenRepository.save(new RefreshTokenEntity(user.getId(), refreshToken.refreshToken(), refreshToken.refreshTokenExpiration().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));
        });

        Date refreshTokenExpiresIn = Date.from(entity.getExpiryTimestamp().atZone(ZoneId.systemDefault()).toInstant());
        return new JwtToken(accessToken, new RefreshToken(entity.getToken(), refreshTokenExpiresIn));
    }
}
