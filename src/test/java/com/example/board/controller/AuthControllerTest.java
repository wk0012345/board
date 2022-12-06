package com.example.board.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@DisplayName("뷰 컨트롤러 인증")
@WebMvcTest
public class AuthControllerTest {
    private final MockMvc mockMvc;

    AuthControllerTest(@Autowired MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }
//
//    @DisplayName("[view] [GET] 로그인 페이지")
//    @Test
//    public void givenNothing_whenTryingToLoggingIn_thenReturnsLoginView() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/login"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.TEXT_HTML));
//    }
}
