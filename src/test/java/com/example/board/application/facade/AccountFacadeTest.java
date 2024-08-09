package com.example.board.application.facade;

import com.example.board.application.dto.response.CanUseMemberIdResponse;
import com.example.board.domain.member.entity.Gender;
import com.example.board.domain.member.entity.Member;
import com.example.board.domain.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountFacadeTest {

    @Autowired
    AccountFacade accountFacade;

    @Autowired
    MemberService memberService;


    @Nested
    @DisplayName("canUseMemberId 테스트")
    class canUseMemberId{

        @Test
        @DisplayName("성공, MemberId가 없을떄 true 반환")
         void whenSuccessMemberIdNotExists(){
            //given --> 테스트할 값
            String memberId = "rokmc";

            //when --> 조건
            CanUseMemberIdResponse canUseMemberIdResponse = accountFacade.canUseMemberId(memberId);


            //then --> 원하는 결과 값
            assertTrue(canUseMemberIdResponse.isStatus());
         }

         @Test
        @DisplayName("성공, MemberId가 있을때 false를 반환한다.")
        void whenSuccessMemberIdExists(){

            //given
             String memberId = "rokmc";

             Member member = new Member(memberId,"password","name","call", Gender.MALE,"nickname","email");

             memberService.save(member);

             //when --> 조건
             CanUseMemberIdResponse canUseMemberIdResponse = accountFacade.canUseMemberId(memberId);


             //then --> 원하는 결과 값
             assertFalse(canUseMemberIdResponse.isStatus());
         }
    }
}