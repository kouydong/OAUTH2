package com.example.oauth2.config;


import com.example.oauth2.domain.service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;

/**
 * AuthorizationServerConfigurer 인터페이스의 3개의 메서드 구현 필요
 * 1. void configure(AuthorizationServerSecurityConfigurer security) throws Exception
 * 2. void configure(ClientDetailsServiceConfigurer clients) throws Exception;
 * 3. void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception;
 * @ahthor Ko Uy-dong
 */


@RequiredArgsConstructor
@Configuration
@EnableAuthorizationServer
public class Oauth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final DataSource dataSource;
    private final CustomUserDetailService userDetailService;

    @Value("${security.oauth2.jwt.signkey}")
    private String signKey;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()") //allow check token
                .allowFormAuthenticationForClients();
    }

    /**
     * 클라이언트 정보 주입 방식을 jdbcdetail로 변경
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
    }

// 스프링 시큐리티 메모리 유저 설정
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//            .withClient("testClientId") //
//            .secret("testSecret") //
//            .redirectUris("http://localhost:8081/oauth2/callback") // 인증(로그인)완료 후 Redirect URI 설정
//            .authorizedGrantTypes("authorization_code") // 인증타입설정(AUTHORIZATION_CODE, IMPLICIT, PASSWORD CREDENTIAL, CLIENT_CREDENTIAL)
//            .scopes("read") // Access Token 으로 접근할 수 있는 Resource 범위
//            .accessTokenValiditySeconds(30000); // Access Token의 유효시간(초)
//    }







    /**
     * 토큰 정보를 DB를 통해 관리한다.
     */
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
//        endpoints.tokenStore(new JdbcTokenStore(dataSource)).userDetailsService(userDetailService);
//    }

    /**
     * 토큰 발급 방식을 JWT 토큰 방식으로 변경한다. 이렇게 하면 토큰 저장하는 DB Table은 필요가 없다.
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        super.configure(endpoints);
        endpoints.accessTokenConverter(jwtAccessTokenConverter()).userDetailsService(userDetailService);
    }

    /**
     * jwt converter - signKey 공유 방식
     */
//    @Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter() {
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setSigningKey(signKey);
//        return converter;
//    }

    /**
     * jwt converter - 비대칭 키 sign
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new FileSystemResource("src/main/resources/oauth2jwt.jks"), "oauth2jwtpass".toCharArray());
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("oauth2jwt"));
        return converter;
    }






}
