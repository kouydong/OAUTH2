package com.example.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 프로젝트에서 사용하는 공통 빈이나 필요한 환경 정보를 설정
 * @author kouydong
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    // 최대 3600초 설정
    private static final long MAX_AGE_SECONDS = 3600;


    /**
     * Cross domain 접근 가능하도록 설정(Cross domain 관련)
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(MAX_AGE_SECONDS);
    }

    /**
     * RestTmeplate 설정(의미가 없어 추후 삭제 예정)
     */
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    /**
     * PasswordEncoder 설정(의미가 없어 추후 삭제 예정)
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
