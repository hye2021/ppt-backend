package com.example.testproject.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import org.springframework.stereotype.Component;

/*
 * JWT를 생성하고 검증하는 유틸 클래스
 */
@Component
public class JwtProvider {

  // JWT 서명을 위한 비밀키 (최소 256비트(32자) 이상)
  private final String SECRET_KEY = "MyJwtSecretKeyMyJwtSecretKey123456";
  private final long EXPIRATION = 1000 * 60 * 60 * 24; // 1일

  // 사용자의 이메일을 기반으로 JWT를 생성
  public String createToken(String email) {
    return Jwts.builder()
        .setSubject(email)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
        .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
        .compact();
  }

  // JWT로부터 이메일을 추출
  public String extractEmail(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(SECRET_KEY.getBytes())
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }
}

