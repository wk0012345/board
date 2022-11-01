package com.example.board.repository;

import com.example.board.config.JpaConfig;
import com.example.board.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("jpa 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
public class JpaRepositoryTest {
    private ArticleRepository articleRepository;
    private ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest(
            @Autowired ArticleRepository articleRepository,
            @Autowired ArticleCommentRepository articleCommentRepository) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("select Test")
    @Test
    void givenTestData_whenSelecting_thenWorksFine() {
        List<Article> articles = articleRepository.findAll();
        assertThat(articles).isNotNull();
    }

    @DisplayName("insert Test")
    @Test
    void givenTestData_whenInserting_thenWorksFine() {

        long previousCount = articleRepository.count();
        Article article = articleRepository.saveAndFlush(Article.of("new Article", "new content", "#spring"));
        assertThat(articleRepository.count()).isEqualTo(previousCount + 1);
    }

    @DisplayName("update Test")
    @Test
    void givenTestData_whenUpdating_thenWorksFine() {

        Article originArticle = articleRepository.findById(1L).orElseThrow();

        String updatedHashtag = "#updated Hashtag";
        originArticle.setHashtag(updatedHashtag);

        long previousCount = articleRepository.count();
        Article article = articleRepository.saveAndFlush(originArticle);
        assertThat(originArticle).hasFieldOrPropertyWithValue("hashtag", updatedHashtag);

    }

    @DisplayName("delete Test")
    @Test
    void givenTestData_whenDeleting_thenWorksFine() {

        Article originArticle = articleRepository.findById(1L).orElseThrow();

        long previousArticleCount  = articleRepository.count();
        long previousArticleCommentCount = articleCommentRepository.count();

        int deletedCommentsSize = originArticle.getArticleComments().size();

        articleRepository.delete(originArticle);

        assertThat(articleRepository.count()).isEqualTo(previousArticleCount - 1);
        assertThat(articleCommentRepository.count()).isEqualTo(previousArticleCommentCount - deletedCommentsSize);

    }
}
