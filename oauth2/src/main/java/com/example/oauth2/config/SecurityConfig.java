package com.example.oauth2.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


 /**
  * 스프링 시큐리티 환경 설정
  * 1. void configure(AuthorizationServerSecurityConfigurer security) throws Exception
  * 2. void configure(ClientDetailsServiceConfigurer clients) throws Exception;
  * 3. void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception;
  *
  * @ahthor Ko Uy-dong
  *
*/
@RequiredArgsConstructor
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomAuthenticationProvider authenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider);
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("{noop}pass")
                .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests().antMatchers("/oauth/**", "/oauth/token", "/oauth2/**", "/h2-console/*", "/oauth/callback").permitAll()
                .and()
                .formLogin().and()
                .httpBasic();
    }
}