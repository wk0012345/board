package com.example.board.repository.querydsl;

import com.example.board.domain.Article;
import com.example.board.domain.QArticle;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ArticleRepositoryCustomImpl extends QuerydslRepositorySupport implements ArticleRepositoryCustom {
    public ArticleRepositoryCustomImpl() {
        super(Article.class);
    }

    @Override
    public List<String> findAllDistinctHashtag() {
        QArticle qArticle = QArticle.article;
        JPQLQuery<String> query = from(qArticle).distinct().select(qArticle.hashtag).where(qArticle.isNotNull());
        return query.fetch();
    }
}
