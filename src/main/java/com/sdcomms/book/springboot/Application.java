package com.sdcomms.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정
@SpringBootApplication
// 메인 클래스
public class Application {
    public static void main(String[] args){
        // 내장 WAS 실행
        SpringApplication.run(Application.class, args);
    }
}
