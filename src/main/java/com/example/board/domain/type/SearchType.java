package com.example.board.domain.type;

import lombok.Getter;

public enum SearchType {
    TITLE("제목"),
    CONTENT("본문"),
    ID("유저 ID"),
    NICKNAME("닉네임"),
    HASHTAG("해시테그");

    @Getter
    private final String description;

    SearchType(String description) {
        this.description = description;
    }
}
