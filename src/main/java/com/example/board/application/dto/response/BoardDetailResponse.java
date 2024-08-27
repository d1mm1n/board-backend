package com.example.board.application.dto.response;


import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardDetailResponse {

    private String title;
    private String content;
    private  int  view;
    private LocalDateTime createdAt;

    public BoardDetailResponse(String title, String content, int view, LocalDateTime createdAt) {
        this.title = title;
        this.content = content;
        this.view = view;
        this.createdAt = createdAt;
    }
}
