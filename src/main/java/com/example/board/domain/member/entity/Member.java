package com.example.board.domain.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //IDENTITY라는 value 값을 알아서 생성해 주겠다!

    private Long id;

    //name은 테이블 에서 정한 변수명, nullable은 null을 허용하지 않아서 false로 설정
    @Column(name="member_id",nullable=false)
    private String memberId;

    @Column(name="password",nullable=false)
    private String password;

    @Column(name="name",nullable=false)
    private String name;

    @Column(name="call",nullable=false)
    private String call;

    @Column(name="gender",nullable=false)
    @Enumerated(EnumType.STRING) // gender를 문자열로 DB에 저장을 하겠다~, EnumType.ORDINAL을 사용하면 정수로 저장 하겠다는 뜻
    private Gender gender;

    @Column(name="nick_name",nullable=false)
    private String nickName;

    @Column(name="email",nullable=false)
    private String email;



}
