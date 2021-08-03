package com.sdcomms.book.springboot.domain.posts;

import com.sdcomms.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 클래스 내 모든 필드의 Getter 메소드를 자동생성
@Getter
// 기본 생성자 자동 추가
@NoArgsConstructor
// 테이블과 링크될 클래스
@Entity
public class Posts extends BaseTimeEntity {

    // PK 필드
    @Id
    // PK 생성 규칙 (auto_increment)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 칼럼
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    // 빌더 패턴 클래스 생성
    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

}
