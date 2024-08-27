package com.example.board.domain.board.service;

import com.example.board.domain.board.entity.Board;
import com.example.board.global.exception.BaseException;
import com.example.board.global.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED) //상속받은 애들만 생성자를 사용
public class BoardService {
    private final BoardRepository boardRepository;

    public Board findById(long id){

        return boardRepository.findById(id).orElseThrow(
                () -> BaseException.from(ErrorCode.BOARD_NOT_FOUND)
        );
        //TODO: 예외처리 작성하기!
    }



    public void save(Board board){
        boardRepository.save(board);
    }
}
