package com.example.testproject.repository;

import com.example.testproject.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByProviderAndProviderId(String provider, String providerId);
}


