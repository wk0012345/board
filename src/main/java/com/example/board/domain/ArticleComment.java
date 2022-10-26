package com.example.board.domain;

import java.time.LocalDateTime;

public class ArticleComment {
    private Long id;
    private Article article; // 게새글 (ID)
    private String content; // 본문

    private LocalDateTime createdAt; // 생성시간
    private String createdBy; // 생성자
    private LocalDateTime modifiedAt; // 수정자
    private String modifiedBy; // 수정시간

}
