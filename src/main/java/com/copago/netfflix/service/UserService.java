package com.copago.netfflix.service;

import com.copago.netfflix.dto.UserInfo;
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
    public UserEntity loadUserByUserId(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("사용자 정보를 찾을 수 없습니다."));
    }

    @Transactional(readOnly = true)
    public UserEntity loadUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("사용자 정보를 찾을 수 없습니다."));
    }

    @Transactional(readOnly = true)
    public UserResponse getUserInfo(UserInfo userInfo) {
        UserEntity entity = loadUserByUserId(userInfo.id());
        return new UserResponse(entity);
    }

    @Transactional
    public UserRegisterResponse registerUser(UserRegisterRequest request) {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new NetfflixException("계정이 이미 존재합니다.");
        }

        UserEntity entity = UserEntity.create(request.email(), passwordEncoder.encode(request.password()), request.userName());
        userRepository.save(entity);

        return new UserRegisterResponse(entity);
    }

    @Transactional
    public UserUpdateResponse updateUser(UserInfo userInfo, UserUpdateRequest request) {
        UserEntity entity = loadUserByUserId(userInfo.id());
        entity.updateInfo(request.userName());

        return new UserUpdateResponse(entity);
    }

    @Transactional
    public UserUpdateResponse updatePassword(UserInfo userInfo, UserPasswordUpdateRequest request) {
        UserEntity entity = loadUserByUserId(userInfo.id());
        entity.updatedPassword(passwordEncoder.encode(request.password()));

        return new UserUpdateResponse(entity);
    }
}