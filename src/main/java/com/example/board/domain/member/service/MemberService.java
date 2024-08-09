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
        return memberRepository.findById(id).orElseThrow(RuntimeException::new); //데이터 베이스에서 아이디로 조회를 해보고 없으면 RuntimeException을 생성

    }
    public Member findByMemberId(String memberId){
        return memberRepository.findByMemberId(memberId).orElseThrow(RuntimeException::new);
    }

    public boolean existsByMemberId(String memberId){
        return memberRepository.existsByMemberId(memberId);
    }
    public boolean notExistsByMemberId(String memberId){
        return !existsByMemberId(memberId);
    }


    public void save(Member member){
        memberRepository.save(member);
    }
}
