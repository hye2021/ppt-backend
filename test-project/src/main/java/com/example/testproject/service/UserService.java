package com.example.testproject.service;

import com.example.testproject.entity.User;
import com.example.testproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public User registerOAuthUser(String provider, String providerId, String email, String name,
      String picture) {
    return userRepository.findByProviderAndProviderId(provider, providerId)
        .orElseGet(() -> userRepository.save(User.builder()
            .provider(provider)
            .providerId(providerId)
            .email(email)
            .name(name)
            .build()));
  }
}
