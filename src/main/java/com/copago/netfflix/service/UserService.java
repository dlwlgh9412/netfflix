package com.copago.netfflix.service;

import com.copago.netfflix.entity.UserEntity;
import com.copago.netfflix.exception.UserNotFoundException;
import com.copago.netfflix.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserEntity loadUserByUsername(String username) {
        return userRepository.findByUserName(username).orElseThrow(() -> new UserNotFoundException("사용자 정보를 찾을 수 없습니다."));
    }
}