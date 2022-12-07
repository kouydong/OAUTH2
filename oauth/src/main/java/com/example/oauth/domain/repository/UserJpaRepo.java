package com.example.oauth.domain.repository;

import com.example.oauth.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepo
{

}

//public interface UserJpaRepo extends JpaRepository<User, Long> {
//    Optional<User> findByUid(String email);
//}
