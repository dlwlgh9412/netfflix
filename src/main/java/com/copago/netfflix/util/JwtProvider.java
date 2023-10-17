package com.copago.netfflix.util;

import com.copago.netfflix.dto.AccessToken;
import com.copago.netfflix.dto.RefreshToken;
import com.copago.netfflix.entity.UserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtProvider {
    private final String issuer;
    private final String secret;
    private final Long accessExpiration;
    private final Long refreshExpiration;

    public JwtProvider(@Value("${jwt.issuer}") String issuer,
                       @Value("${jwt.secret}") String secret,
                       @Value("${jwt.accessExpiration}") Long accessExpiration,
                       @Value("${jwt.refreshExpiration}") Long refreshExpiration) {
        this.issuer = issuer;
        this.secret = secret;
        this.accessExpiration = accessExpiration;
        this.refreshExpiration = refreshExpiration;
    }

    public AccessToken generateAccessToken(UserEntity user) {
        final long now = System.currentTimeMillis();
        Date accessTokenExpiration = new Date(now + accessExpiration);
        String token = Jwts.builder()
                .setIssuer(issuer)
                .setSubject(user.getUserName())
                .setAudience("USER")
                .setIssuedAt(new Date(now))
                .setExpiration(accessTokenExpiration)
                .signWith(SignatureAlgorithm.HS512, secret)
                .setClaims(Map.of("roles", user.getAuthorities()))
                .compact();

        return new AccessToken(token, accessTokenExpiration);
    }

    public RefreshToken generateRefreshToken(UserEntity user) {
        final long now = System.currentTimeMillis();
        Date refreshTokenExpiration = new Date(now + refreshExpiration);
        String token = Jwts.builder()
                .setIssuer(issuer)
                .setSubject(user.getUserName())
                .setAudience("USER")
                .setIssuedAt(new Date(now))
                .setExpiration(refreshTokenExpiration)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

        return new RefreshToken(token, refreshTokenExpiration);
    }
}
