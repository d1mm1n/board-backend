package com.example.board.application.controller;


import com.example.board.application.dto.request.BoardWriteRequest;
import com.example.board.application.dto.request.CommentWriteRequest;
import com.example.board.application.dto.response.BoardDetailResponse;
import com.example.board.application.dto.response.BoardListResponse;
import com.example.board.application.facade.BoardFacade;
import com.example.board.application.facade.CommentFacade;
import com.example.board.domain.board.entity.Board;
import com.example.board.domain.board.service.BoardService;
import com.example.board.domain.comment.service.CommentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardController {

    private final CommentFacade commentFacade;
    private final BoardFacade boardFacade;
    @PostMapping()
    public ResponseEntity<?> writeBoard(@RequestBody BoardWriteRequest boardWriteRequest) {

        String title = boardWriteRequest.getTitle();
        String content = boardWriteRequest.getContent();

        boardFacade.writeBoard(title, content);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardDetailResponse> getBoard(@PathVariable(name="id") long id) {

        BoardDetailResponse boardDetailResponse = boardFacade.getBoard(id);

        return ResponseEntity.ok(boardDetailResponse);
    }

    @PostMapping("/{id}/comment")
    public ResponseEntity<Void> writeComment(@PathVariable(name="id") long boardId, @RequestBody CommentWriteRequest commentWriteRequest){

        String content = commentWriteRequest.getContent();
        commentFacade.writeComment(boardId,content);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping()
    public ResponseEntity<List<BoardListResponse>> getAllBoards() {
        boardFacade.getAllBoard();

        return ResponseEntity.ok(boardFacade.getAllBoard());
    }
}
