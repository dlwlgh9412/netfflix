package com.copago.netfflix.web.controller;

import com.copago.netfflix.service.UserService;
import com.copago.netfflix.dto.UserInfo;
import com.copago.netfflix.web.dto.UserUpdateRequest;
import com.copago.netfflix.web.dto.UserPasswordUpdateRequest;
import com.copago.netfflix.web.dto.UserRegisterRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @PostMapping
    @Operation(summary = "회원가입")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegisterRequest request) {
        return new ResponseEntity<>(userService.registerUser(request), HttpStatus.CREATED);
    }

    @PatchMapping("/infos")
    @Operation(summary = "사용자 정보 수정")
    public ResponseEntity<?> modifyUser(UserInfo userInfo, @Valid @RequestBody UserUpdateRequest request) {
        return new ResponseEntity<>(userService.updateUser(userInfo, request), HttpStatus.OK);
    }

    @PatchMapping("/password")
    @Operation(summary = "패스워드 변경")
    public ResponseEntity<?> modifyPassword(UserInfo userInfo, @Valid @RequestBody UserPasswordUpdateRequest request) {
        return new ResponseEntity<>(userService.updatePassword(userInfo, request), HttpStatus.OK);
    }
}
