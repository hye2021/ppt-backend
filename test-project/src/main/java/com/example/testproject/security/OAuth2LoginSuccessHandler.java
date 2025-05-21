package com.example.testproject.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/*
 * OAuth2 로그인 성공 시 자동으로 실행 되는 후처리 핸들러
 * AuthenticationSuccessHandler: 로그인 성공 시 동작할 커스텀 핸들러
 * */
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

  private final JwtProvider jwtProvider;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException {

    // 로그인된 사용자 객체에서 email을 꺼냄
    OAuth2User user = (OAuth2User) authentication.getPrincipal();
    String email = user.getAttribute("email");

    // email기반으로 -> JWT 생성
    String token = jwtProvider.createToken(email);

    // 클라이언트에게 JWT 전달 방식 (1. URL, 2. 쿠키, 3. JSON 중 택1)
    // 여기선 URL 리디렉션에 포함하는 방식 예시
    response.sendRedirect("/login/success?token=" + token);
  }
}

