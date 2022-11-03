package com.example.board.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@Disabled("통합테스트는 불필요하므로 제외시킴")
@DisplayName("Data rest api test")
@Transactional
@AutoConfigureMockMvc
@SpringBootTest
public class DataRestTest {
    private final MockMvc mockMvc;

    public DataRestTest(@Autowired MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @DisplayName("[api]게시글 리스트 단건 조회")
    @Test
    void given_whenRequestingArticle_thenReturnsArticlesJsonResponse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/articles")).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.valueOf("application/hal+json")));
    }

    @DisplayName("[api]게시글 리스트 -> 댓글 리스트 조회")
    @Test
    void given_whenRequestingArticleCommentsFromArticle_thenReturnsArticleCommentsJsonResponse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/articles/1/articleComments")).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.valueOf("application/hal+json")));
    }

    @DisplayName("[api]게시글 댓글 리스트 조회")
    @Test
    void given_whenRequestingArticleComments_thenReturnsArticleCommentsJsonResponse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/articleComments")).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.valueOf("application/hal+json")));
    }

    @DisplayName("[api]게시글 댓글 단건 조회")
    @Test
    void given_whenRequestingArticleComment_thenReturnsArticleCommentJsonResponse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/articleComments/1")).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.valueOf("application/hal+json")));
    }
}
