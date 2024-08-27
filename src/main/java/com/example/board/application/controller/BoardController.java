package com.example.board.application.controller;


import com.example.board.application.dto.request.BoardWriteRequest;
import com.example.board.application.dto.response.BoardDetailResponse;
import com.example.board.application.facade.BoardFacade;
import com.example.board.domain.board.entity.Board;
import com.example.board.domain.board.service.BoardService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardController {

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

}
