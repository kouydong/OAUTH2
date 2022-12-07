package com.example.oauth2.domain.repository;

import com.example.oauth2.domain.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserJpaRepo extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByUid(String email);
}
