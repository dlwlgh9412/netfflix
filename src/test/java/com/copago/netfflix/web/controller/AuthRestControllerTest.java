package com.copago.netfflix.web.controller;

import com.copago.netfflix.service.AuthService;
import com.copago.netfflix.util.JwtProvider;
import com.copago.netfflix.web.dto.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AuthRestController.class)
class AuthRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @MockBean
    private JwtProvider jwtProvider;

    private String emailValidMessage = "이메일은 필수 입력 값 입니다.";
    private String passwordValidMessage = "비밀번호는 필수 입력 값 입니다.";

    @Test
    public void 로그인_시도_유효성_체크_아이디_비밀번호가_비어있을_경우() throws Exception {
        LoginRequest loginRequest = new LoginRequest("", " ");

        mockMvc.perform(post("/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(loginRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.email", Matchers.is(emailValidMessage)))
                .andExpect(jsonPath("$.errors.password", Matchers.is(passwordValidMessage)));
    }

    @Test
    public void 로그인_시도_유효성_체크_아이디_비어있을_경우() throws Exception {
        LoginRequest loginRequest = new LoginRequest("", "Password");

        mockMvc.perform(post("/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(loginRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.email", Matchers.is(emailValidMessage)));
    }

    @Test
    public void 로그인_시도_유효성_체크_비밀번호_비어있을_경우() throws Exception {
        LoginRequest loginRequest = new LoginRequest("Id", "");

        mockMvc.perform(post("/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(loginRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.password", Matchers.is(passwordValidMessage)));
    }


}