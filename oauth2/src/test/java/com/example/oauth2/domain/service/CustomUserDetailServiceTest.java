package com.example.oauth2.domain.service;

import com.example.oauth2.domain.entity.UserInfo;
import com.example.oauth2.domain.repository.UserInfoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;


@SpringBootTest(properties = "spring.profiles.active=local")
class CustomUserDetailServiceTest {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Test
    @DisplayName("인서트 기본 유저")
    public void insertDefaultUser() {

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        System.out.printf("1234 : %s\n", passwordEncoder.encode("1234"));

    }

    @Test
    public void insertNewUser() {
        userInfoRepository.save(UserInfo.builder()
                .uid("jaden@apti.co.kr")
                .password(passwordEncoder.encode("1234"))
                .name("jaden")
                .roles(Collections.singletonList("ROLE_USER"))
                .build());
    }

}


