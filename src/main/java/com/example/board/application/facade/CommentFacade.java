package com.example.board.application.facade;


import com.example.board.domain.board.entity.Board;
import com.example.board.domain.board.service.BoardService;
import com.example.board.domain.comment.entity.Comment;
import com.example.board.domain.comment.service.CommentService;
import com.example.board.domain.member.entity.Member;
import com.example.board.domain.member.service.MemberService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentFacade {

    //서비스에서 정보를 가져오는 것
    private final MemberService memberService;
    private final BoardService boardService;
    private final CommentService commentService;


    public void writeComment(long boardId, String content) {
        //댓글을 작성하기 위해서는
        //작성자 정보 필요
        //게시물 정보도 필요

        Member member = memberService.findById(1L);  //항상 첫번 째 사용자가 댓글을 등록한다.
        Board board = boardService.findById(boardId);

        Comment comment = new Comment(board,member,content);

        commentService.save(comment);

    }

}
