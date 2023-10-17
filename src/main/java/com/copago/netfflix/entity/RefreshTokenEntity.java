package com.copago.netfflix.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "tb_refresh_token")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "expiry_timestamp", nullable = false)
    private LocalDateTime expiryTimestamp;

    public RefreshTokenEntity(Long userId, String token, LocalDateTime expiryTimestamp) {
        this.userId = userId;
        this.token = token;
        this.expiryTimestamp = expiryTimestamp;
    }
}
