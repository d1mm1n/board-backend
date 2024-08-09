package com.example.board.domain.member.service;

import com.example.board.domain.member.entity.Gender;
import com.example.board.domain.member.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;


    @Nested
    @DisplayName("findById 테스트")
    class findById{

        @Test
        @DisplayName("실패, 데이터가 없는 경우")
        void whenFailDataNotExists() {

            Assertions.assertThrows(RuntimeException.class, () -> {
                memberService.findById(1L);

            });
        }

        @Test
        @DisplayName("성공, 데이터가 존재하는 경우")
        void WhenSuccessDataExists() {
            Member member = new Member( //객체 생성
                    "memberId", "password","name","call", Gender.FEMALE,"nickName","email"
            );
            
            
            System.out.println(member.getId()); //null 출력 됨
            memberService.save(member);  // 이 함수 호출로 member는 JPA가 관리하는 영역이 된거임
            Assertions.assertNotNull(member.getId());
            long id = member.getId();
            memberService.findById(id);

            assertEquals(member.getId(),memberService.findById(id).getId());

        }

    }

}