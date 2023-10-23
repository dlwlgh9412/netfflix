package com.copago.netfflix.service;

import com.copago.netfflix.entity.UserEntity;
import com.copago.netfflix.exception.NetfflixException;
import com.copago.netfflix.exception.UserNotFoundException;
import com.copago.netfflix.repository.UserRepository;
import com.copago.netfflix.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public UserEntity loadUserByUsername(String userId) {
        return userRepository.findByUserId(userId).orElseThrow(() -> new UserNotFoundException("사용자 정보를 찾을 수 없습니다."));
    }

    @Transactional(readOnly = true)
    public UserResponse getUserInfo(String id) {
        UserEntity user = loadUserByUsername(id);
        return new UserResponse(user);
    }

    @Transactional
    public UserRegisterResponse registerUser(UserRegisterRequest request) {
        if (userRepository.findByUserId(request.id()).isPresent()) {
            throw new NetfflixException("계정이 이미 존재합니다.");
        }

        UserEntity entity = UserEntity.create(request.id(), passwordEncoder.encode(request.password()), request.userName());
        userRepository.save(entity);

        return new UserRegisterResponse(entity.getUserId(), entity.getCreatedAt());
    }

    @Transactional
    public UserUpdateResponse updateUser(String id, UserUpdateRequest request) {
        userRepository.findByUserId(id).ifPresent(user -> {
            user.updateInfo(request.userName());
        });

        throw new UserNotFoundException("사용자 정보를 찾을 수 없습니다.");
    }

    @Transactional
    public UserUpdateResponse updatePassword(String id, UserPasswordUpdateRequest request) {
        userRepository.findByUserId(id).ifPresent(user -> {
            user.updatedPassword(passwordEncoder.encode(request.password()));
        });

        throw new UserNotFoundException("사용자 정보를 찾을 수 없습니다.");
    }
}