package com.example.board.controller;

import com.example.board.domain.type.SearchType;
import com.example.board.dto.response.ArticleResponse;
import com.example.board.dto.response.ArticleWithCommentsResponse;
import com.example.board.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * articles
 */
@RequestMapping("/articles")
@Controller
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    @GetMapping
    public String articles(
            @RequestParam(required = false) SearchType searchType,
            @RequestParam(required = false) String searchValue,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            ModelMap modelMap) {
        modelMap.addAttribute("articles", articleService.searchArticles(searchType, searchValue, pageable).map(ArticleResponse::from));
        return "articles/index";
    }

    @GetMapping("/{articleId}")
    public String article(@PathVariable long articleId, ModelMap modelMap) {
        ArticleWithCommentsResponse articleWithCommentsResponse = ArticleWithCommentsResponse.from(articleService.getArticle(articleId));
        modelMap.addAttribute("article", articleWithCommentsResponse);
        modelMap.addAttribute("articleComments", articleWithCommentsResponse.articleCommentResponses());
        return "articles/detail";
    }
}
