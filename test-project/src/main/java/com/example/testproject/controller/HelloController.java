package com.example.testproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class HelloController {

  @GetMapping("/Hello")
  public Map<String, String> hello() {
    return Map.of("message", "Hellow World");
  }

  @GetMapping("/login/success")
  public String loginSuccess(@RequestParam String token) {
    return "JWT Token: " + token;
  }

  @GetMapping("/api/user/me")
  public ResponseEntity<?> me(@AuthenticationPrincipal OAuth2User user) {
    return ResponseEntity.ok(user.getAttributes());
  }
}
