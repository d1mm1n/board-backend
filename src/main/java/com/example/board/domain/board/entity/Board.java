package com.example.board.domain.board.entity;


import com.example.board.domain.comment.entity.Comment;
import com.example.board.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 이거 추가해주면 알아서 매개변수 없는 생성자를 자동으로 만들어 줌, Protected로 해주면 jpa에서만 접근 가능
@EntityListeners(AuditingEntityListener.class)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="member_id") //데이터 베이스에서 스키마 생성할 때 컬럼명 뭘로 지정할꺼냐?
    private Member member;

    @Column(name="title",nullable = false)
    private String title;

    @CreatedDate //SQL에서 insert 할 때 알아서 insert 해줌
    @Column(name="created_at",nullable = false)
    private LocalDateTime createdAt;

    @Setter
    @ColumnDefault("0") // view의 기본 값을 0으로 설정, 추론을 못해서 string 으로 작성해줘야함 -> "0" 이렇게
    @Column(name="view",nullable = false)
    private int view;

    @Column(name="content",nullable = false)
    private String content;


    @OneToMany(mappedBy= "board") //1대다(1:N) 관계에서 mappedBy가 필요하다.
    //mappedBy에 들어가는 String은 Comment Class의 board의 "필드명"이다.
    private List<Comment> commentList;

    public Board(Member member, String title, String content) {
        this.member = member;
        this.title = title;
        this.content = content;
        this.view = 0;
    }
}
