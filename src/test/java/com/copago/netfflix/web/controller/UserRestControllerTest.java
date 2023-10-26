package com.copago.netfflix.web.controller;

import com.copago.netfflix.service.UserService;
import com.copago.netfflix.web.dto.UserRegisterRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
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


    @Test
    public void 회원가입_이메일_값이_비어있는_경우() throws Exception {
        UserRegisterRequest request = new UserRegisterRequest("", "Password", "이름");

        mockMvc.perform(post("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.email", Matchers.is("이메일은 필수 입력 값 입니다.")));
    }

    @Test
    public void 이메일_형식이_아닌_경우() throws Exception {
        UserRegisterRequest request = new UserRegisterRequest("test", "Password", "이름");
        mockMvc.perform(post("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.email", Matchers.is("이메일 형식이 아닙니다.")));
    }

    @Test
    public void 유저_이름이_빈_값일_경우() throws Exception {
        UserRegisterRequest request = new UserRegisterRequest("test", "Password", "");
        mockMvc.perform(post("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.userName", Matchers.is("이름은 필수 입력 값 입니다.")));
    }
}