package com.example.board.controller;

import com.example.board.config.SecurityConfig;
import com.example.board.domain.type.SearchType;
import com.example.board.dto.ArticleWithCommentsDto;
import com.example.board.service.ArticleService;
import com.example.board.service.PaginationService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;


@DisplayName("View controller articles")
@Import(SecurityConfig.class)
@WebMvcTest
class ArticleControllerTest {
    private final MockMvc mockMvc;

    @MockBean
    private ArticleService articleService;

    @MockBean
    private PaginationService paginationService;

    ArticleControllerTest(@Autowired MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @DisplayName("[view] [GET] 게시글 리스트")
    @Test
    public void givenNothing_whenRequestArticlesView_thenReturnArticlesView() throws Exception {

        given(articleService.searchArticles(eq(null), eq(null), any(Pageable.class))).willReturn(Page.empty());
        given(paginationService.getPaginationBarNumbers(anyInt(), anyInt())).willReturn(List.of(0, 1, 2, 3, 4));

        mockMvc.perform(MockMvcRequestBuilders.get("/articles"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(MockMvcResultMatchers.view().name("articles/index"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("articles"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("paginationBarNumbers"));
//                .andExpect(MockMvcResultMatchers.model().attributeExists("searchTypes"));
        then(articleService).should().searchArticles(eq(null), eq(null), any(Pageable.class));
        then(paginationService).should().getPaginationBarNumbers(anyInt(), anyInt());
    }

    @DisplayName("[view] [GET] 게시글 리스트 검색")
    @Test
    public void givenSearchKeyword_whenRequestArticlesView_thenReturnArticlesView() throws Exception {

        SearchType searchType = SearchType.TITLE;
        String searchValue = "title";

        given(articleService.searchArticles(eq(searchType), eq(searchValue), any(Pageable.class))).willReturn(Page.empty());
        given(paginationService.getPaginationBarNumbers(anyInt(), anyInt())).willReturn(List.of(0, 1, 2, 3, 4));


        mockMvc.perform(MockMvcRequestBuilders.get("/articles").queryParam("searchType", searchType.name()).queryParam("searchValue", searchValue))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(MockMvcResultMatchers.view().name("articles/index"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("articles"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("paginationBarNumbers"));
//                .andExpect(MockMvcResultMatchers.model().attributeExists("searchTypes"));
        then(articleService).should().searchArticles(eq(searchType), eq(searchValue), any(Pageable.class));
        then(paginationService).should().getPaginationBarNumbers(anyInt(), anyInt());
    }


    @Disabled("구현중")
    @DisplayName("[view] [GET] 게시글 상세")
    @Test
    public void givenNothing_whenRequestArticleView_thenReturnArticleView() throws Exception {

        Long articleId = 1L;
        given(articleService.getArticle(articleId)).willReturn(any(ArticleWithCommentsDto.class));

        mockMvc.perform(MockMvcRequestBuilders.get("/articles/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.TEXT_HTML))
                .andExpect(MockMvcResultMatchers.view().name("articles/detail"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("article"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("articleComments"));
        then(articleService).should().getArticle(articleId);
    }


    @Disabled("구현중")
    @DisplayName("[view] [GET] 게시글 검색 전용 페이지 ")
    @Test
    public void givenNothing_whenRequestArticleSearchView_thenReturnArticleSearchView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/articles/search"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("articles/search"))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.TEXT_HTML));

    }

    @Disabled("구현중")
    @DisplayName("[view] [GET] 게시글 해시테그 검색 페이지 ")
    @Test
    public void givenNothing_whenRequestArticleHashtagSearchView_thenReturnArticleHashtagSearchView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/articles/search-hashtag"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("articles/search-hashtag"))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.TEXT_HTML));

    }

}