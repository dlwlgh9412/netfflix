package com.copago.netfflix.web.controller;

import com.copago.netfflix.service.UserService;
import com.copago.netfflix.web.dto.UserUpdateRequest;
import com.copago.netfflix.web.dto.UserPasswordUpdateRequest;
import com.copago.netfflix.web.dto.UserRegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegisterRequest request) {
        return new ResponseEntity<>(userService.registerUser(request), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/info")
    public ResponseEntity<?> modifyUser(@PathVariable("id") String id, @Valid @RequestBody UserUpdateRequest request) {
        return new ResponseEntity<>(userService.updateUser(id, request), HttpStatus.OK);
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<?> modifyPassword(@PathVariable("id") String id, @Valid @RequestBody UserPasswordUpdateRequest request) {
        return new ResponseEntity<>(userService.updatePassword(id, request), HttpStatus.OK);
    }
}
