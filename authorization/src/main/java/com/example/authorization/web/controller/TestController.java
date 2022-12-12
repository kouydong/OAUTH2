package com.example.authorization.web.controller;

import com.example.authorization.config.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/api/common", produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
@RequiredArgsConstructor
public class TestController {





    /**
     * JWT 토큰 발급 프로세스
     * @param clientId     클라이언트ID
     * @param clientSecret 클라이언트Secret
     * @return JWT 토큰 문자열
     * @throws Exception
     */
    @GetMapping("/token")
    public String createToken(@RequestHeader(value = "clientId") String clientId,
                              @RequestHeader(value = "clientSecret") String clientSecret) throws Exception {
        // Ab56IgawRqZBCkjKKS3vGQ== SAMSUNGCNT

        // To do : clientId 및 clientSecret 조회 로직 구현 필요
        // 예외로직 추가 필요
        TokenProvider tokenProvider = new TokenProvider();

        return tokenProvider.createToken(clientId);
    }


    /**
     * JWT 토큰 유효성 검증
     * @param token     클라이언트ID
     * @return JWT 토큰 유효성 검증
     * @throws Exception
     */
    @GetMapping("/validation")
    public void createToken(@RequestHeader(value = "token") String token) throws Exception {

        TokenProvider tokenProvider = new TokenProvider();

        tokenProvider.validateToken(token);

    }


}