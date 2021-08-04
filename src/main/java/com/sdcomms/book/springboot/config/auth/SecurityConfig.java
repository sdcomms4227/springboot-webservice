package com.sdcomms.book.springboot.config.auth;

import com.sdcomms.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
// Spring Security 설정들을 활성화시켜 줍니다.
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // h-2console 화면을 사용하기 위해 해당 옵션들을 disable 합니다.
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    // URL별 권한 관리를 설정하는 옵션의 시작점입니다.
                    // authrizeRequests가 선언되어야만 antMatchers 옵션을 사용할 수 있습니다.
                    .authorizeRequests()
                    // 권한 관리 대상을 지정하는 옵션입니다.
                    // permitAll() : 전체권한
                    // hasRole : 지정된 사용자(USER) 권한
                    .antMatchers("/","/css/**","/images/**","/js/**","h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    // 설정된 값들 이외 나머지 URL들을 나타냅니다.
                    // authenticated : 인증된 사용자들에게만 허용
                    .anyRequest().authenticated()
                .and()
                    // 로그아웃 기능에 대한 여러 설정의 진입점입니다.
                    .logout()
                        // 로그아웃 성공시 / 주소로 이동합니다.
                        .logoutSuccessUrl("/")
                .and()
                    // OAuth 2 로그인 기능에 대한 여러 설정의 진입점입니다.
                    .oauth2Login()
                        // OAuth 2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당합니다.
                        .userInfoEndpoint()
                            // 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록합니다.
                            .userService(customOAuth2UserService);
    }
}
