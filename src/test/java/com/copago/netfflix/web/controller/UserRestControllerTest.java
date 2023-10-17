package com.copago.netfflix.web.controller;

import com.copago.netfflix.service.UserService;
import com.copago.netfflix.web.dto.RegisterUserRequest;
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
@WebMvcTest(UserRestController.class)
class UserRestControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    private String idValidMessage = "아이디는 필수 입력 값 입니다.";
    private String passwordValidMessage = "비밀번호가 일치하지 않습니다.";

    @Test
    public void 회원가입_아이디_값이_비어있는_경우() throws Exception {
        RegisterUserRequest request = new RegisterUserRequest("", "Password", "Password");

        mockMvc.perform(post("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors.id", Matchers.is(idValidMessage)));
    }

    @Test
    public void 회원가입_비밀번호_값이_일치하지_않은_경우() throws Exception {
        RegisterUserRequest request = new RegisterUserRequest("Test_ID", "", "Password");

        mockMvc.perform(post("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors.matchingPassword", Matchers.is(passwordValidMessage)));
    }
}