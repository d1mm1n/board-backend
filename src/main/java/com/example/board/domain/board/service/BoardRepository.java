package com.example.board.domain.board.service;


import com.example.board.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
//JpaRepository는 bao 기능을 담당
interface BoardRepository extends JpaRepository<Board, Long> {


}
