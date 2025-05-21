package com.example.testproject.config;

import com.example.testproject.security.OAuth2LoginSuccessHandler;
import com.example.testproject.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final CustomOAuth2UserService customOAuth2UserService;
  private final OAuth2LoginSuccessHandler successHandler;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http,
      OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler) throws Exception {
    http
        .csrf(CsrfConfigurer::disable)
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/", "/login/**", "/oauth2/**").permitAll()
            .anyRequest().authenticated() // 나머지는 인증 필요
        )
        .oauth2Login(oauth -> oauth
            .userInfoEndpoint(userInfo -> userInfo
                .userService(customOAuth2UserService) // 사용자 정보 처리
            )
            .successHandler(successHandler)
        );
    return http.build();
  }
}
