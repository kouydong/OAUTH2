package com.example.authorization.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.List;


@Configuration
@RequiredArgsConstructor
public class TokenProvider {
//    private final String secretKey = "";
//    private Key key;

    private Key key = Keys.hmacShaKeyFor("236979CB6F1AD6B6A6184A31E6BE37DB3818CC36871E26235DD67DCFE4041492".getBytes());
    // 토큰 유효시간 10분
    private long tokenValidTime = 10 * 60 * 1000L;


    public String createToken(String clientId) {

        Date now = new Date();

        return Jwts.builder()
                // Header area
                .setHeaderParam("typ", "jwt") // header 값에 jwt 토큰임을 명시
                // Payload area(등록된 클레임 방식 사용)
                .setIssuer("APT-I")       // 토큰발행자(iss)
                .setSubject("APT-I Access Token")      // 클레임제목(sub)
                .setAudience(clientId)    // 토큰대상자(aud)
                .setIssuedAt(now)         // 토큰발행시간(iat)
                .setExpiration(new Date(now.getTime() + tokenValidTime)) // 토큰만기시간정보(exp)
                .setId(clientId + now)                // 아이디(jti)
                .setNotBefore(now)        // 토큰활성시간(nbf)
                // Signature area
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }


    // 토큰의 유효성 검증
    public boolean validateToken(String jwtToken) {
        try {

            System.out.println("시작 합니다==>" + jwtToken);

            Claims claims =
                    Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwtToken)
                    .getBody();

            System.out.println("토큰 Claim 값 ==>" + claims);

            return true;

        } catch (Exception e) {
            System.out.println("예외 발생 " + e.getMessage());
            return false;
        }
    }
}