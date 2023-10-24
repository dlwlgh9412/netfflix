package com.copago.netfflix.entity;

import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_user", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id"}))
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "password")
    private String password;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "is_account_non_expired")
    private Boolean isAccountNonExpired = true;

    @Column(name = "is_account_non_locked")
    private Boolean isAccountNonLocked = true;

    @Column(name = "is_credentials_non_expired")
    private Boolean isCredentialsNonExpired = true;

    @Column(name = "is_enabled")
    private Boolean isEnabled = true;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @ManyToMany
    @JoinTable(name = "tb_user_authority",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority"))
    private List<AuthorityEntity> authorities;

    public Collection<AuthorityEntity.Name> getAuthorities() {
        return this.authorities.stream().map(AuthorityEntity::getAuthority).collect(Collectors.toSet());
    }

    private UserEntity(String userId, String password, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public void updateInfo(String userName) {
        this.userName = userName;
        this.updatedAt = LocalDateTime.now();
    }

    public void updatedPassword(String password) {
        if (StringUtils.hasText(password)) {
            this.password = password;
        }

        throw new RuntimeException("패스워드 값이 비어있습니다.");
    }

    public static UserEntity create(String userId, String password, String userName) {
        return new UserEntity(userId, password, userName);
    }
}
