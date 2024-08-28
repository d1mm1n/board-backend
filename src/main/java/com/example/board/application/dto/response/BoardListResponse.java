package com.example.board.application.dto.response;

import com.example.board.domain.board.entity.Board;
import com.example.board.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardListResponse {
    //제목, 작성자, 작성시간, 댓글 갯수, 조회수, 추천 수-> 기본 0으로 세팅

    private long id;
    private String title;
    private LocalDateTime createdAt;
    private int commentCount;
    private int view;
    private int recommendCount;

    private MemberInBoardListResponse member;

    @Getter
    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    private static class MemberInBoardListResponse {
        private final long id;
        private final String nickName;


        public static MemberInBoardListResponse from(Member member) {
            return new MemberInBoardListResponse(member.getId(), member.getNickName());
        }
    }

    public static BoardListResponse from(Board board) {
        return new BoardListResponse(board.getId(),board.getTitle()
                ,board.getCreatedAt(),
                board.getCommentList().size(),
                board.getView(),
                0,
                MemberInBoardListResponse.from(board.getMember()));
    }
}
