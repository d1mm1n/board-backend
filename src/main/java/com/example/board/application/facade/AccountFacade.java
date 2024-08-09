package com.example.board.application.facade;


import com.example.board.application.dto.response.CanUseMemberIdResponse;
import com.example.board.domain.member.entity.Member;
import com.example.board.domain.member.service.MemberService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountFacade {

    private final MemberService memberService;

    public CanUseMemberIdResponse canUseMemberId(String memberId){
        //1. 멤버 서지스에서 유저를 memberId로 조회를 한다.
        //2. 존재하면 false 존재하지 않으면 true 반환.

        //존재 하지 않으면 Runtime Exception이 발생
        //존재 하면 그냥 작업이 될것이다.

//        try{
//            Member member = memberService.findByMemberId(memberId);
//            return false;
//        }catch (RuntimeException e){
//            return true;
//        }
        boolean status= memberService.notExistsByMemberId(memberId);
        return new CanUseMemberIdResponse(status);

    }
}
