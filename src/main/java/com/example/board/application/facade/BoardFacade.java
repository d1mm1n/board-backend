package com.example.board.application.facade;


import com.example.board.application.dto.response.BoardDetailResponse;
import com.example.board.domain.board.entity.Board;
import com.example.board.domain.board.service.BoardService;
import com.example.board.domain.member.entity.Member;
import com.example.board.domain.member.service.MemberService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)

public class BoardFacade {

    private final BoardService boardService;
    private final MemberService memberService;

    public void writeBoard(String title, String content) {

        Member member = memberService.findById(1L); //첫번째 회원가입한 회원만 글을 작성할 수 있음.
        Board board = new Board(member, title, content);
        boardService.save(board);
    }
    public BoardDetailResponse getBoard(long id) {
        Board board = boardService.findById(id);
        boardService.increaseViewOnce(board);



        return BoardDetailResponse.from(board);
    }

}
