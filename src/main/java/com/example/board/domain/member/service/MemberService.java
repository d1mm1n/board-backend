package com.example.board.domain.member.service;

import com.example.board.domain.comment.entity.Comment;
import com.example.board.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberService {
    private final MemberRepository memberRepository;

    public Member findById(long id){
        return memberRepository.findById(id).orElseThrow(RuntimeException::new);

    }

    public void save(Member member){
        memberRepository.save(member);
    }
}
