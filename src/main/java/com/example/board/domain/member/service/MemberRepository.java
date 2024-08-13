package com.example.board.domain.member.service;

import com.example.board.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberId(String memberId);  //Optional은 null일때 어떤 작업을 할 수 있도록

    boolean existsByMemberId(String memberId);
}
