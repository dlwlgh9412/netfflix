package com.copago.netfflix.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_authority")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AuthorityEntity {
    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "authority")
    private Name authority;

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    private List<UserEntity> users;

    @Getter
    public enum Name {
        ADMIN, USER, ANONYMOUS
    }
}
