package com.example.board.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@ToString
@Table
public class Article {
    private Long id;
    private String title; // 제목
    private String content; // 본문
    private String hashtag; // 해시테그

    private LocalDateTime createdAt; // 생성시간
    private String createdBy; // 생성자
    private LocalDateTime modifiedAt; // 수정자
    private String modifiedBy; // 수정시간

}
