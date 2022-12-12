package com.example.oauth2.config;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


/**
  * 스프링 시큐리티 환경 설정
  * 1. void configure(AuthorizationServerSecurityConfigurer security) throws Exception
  * 2. void configure(ClientDetailsServiceConfigurer clients) throws Exception;
  * 3. void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception;
  *
  * @author Ko Uy-dong
  *
*/

// @EnableWebSecurity
// @RequiredArgsConstructor
// @Configuration(proxyBeanMethods = false)
// @ConditionalOnDefaultWebSecurity
// @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
// public class SecurityConfig {

    ////    @Bean
////    public UserDetailsService userDetailsService() {
////        return new ShopmeUserDetailsService();
////    }
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//
//    @Bean
//    // 스프링 부트에서 default 값으로 Security Filter Chain 등록하는데 @Bean 객체를 통해서 다시 주입하면서 예외가 발생하여 순서를 설정
//    @Order(SecurityProperties.BASIC_AUTH_ORDER-10)
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .headers().frameOptions().disable()
//                .and()
//                .authorizeRequests().antMatchers("/oauth/**", "/oauth/token", "/oauth2/**", "/h2-console/*", "/oauth/callback").permitAll()
//                .and()
//                .formLogin().and()
//                .httpBasic();
//        return http.build();
//    }
//}

//        http
//                .authorizeHttpRequests(authorize -> authorize
//                .anyRequest().authenticated()
//        )
//                .formLogin(withDefaults());
////                .httpBasic(withDefaults());
////
////        http
////                .authorizeRequests()
////                .antMatchers("/").permitAll()
////                .antMatchers(
////                        "/css/**",
////                        "/js/**",
////                        "/images/**",
////                        "/webjars/**",
////                        "/favicon.*",
////                        "/*/icon-*"
////                ).permitAll()
////                .antMatchers("/mypage").hasRole("USER")
////                .antMatchers("/message").hasRole("MANAGER")
////                .antMatchers("/config").hasRole("ADMIN")
////                .anyRequest().authenticated();
////
////        http
////                .formLogin();
//
//        return http.build();
//
////                formLogin()
////                .loginPage("/loginPage")
////                .defaultSuccessUrl("/")
////                .failureUrl("/login")
////                .usernameParameter("userId")
////                .passwordParameter("passwd")
////                .loginProcessingUrl("/login_proc")
////                .permitAll();
////        return httpSecurity.build();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("password")
//                .roles("USER")
//                .build();
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("password")
//                .roles("ADMIN", "USER")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }



//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
//    }
//


// }



//@RequiredArgsConstructor
//@EnableAuthorizationServer
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomAuthenticationProvider authenticationProvider;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password("{noop}pass")
//                .password("pass")
//                .roles("USER");
    }

// http://localhost:8081/oauth/authorize?client_id=testClientId&redirect_uri=http://localhost:8081/oauth2/callback&response_type=code&scope=read

    // http://localhost:8081/oauth/authorize?client_id=testClientId&redirect_uri=http://localhost:8081/oauth2/callback&response_type=code&scope=read
    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security
                .csrf().disable()   // csrf 사용안함
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests().antMatchers("/kouydong/**", "/oauth/**", "/oauth/token", "/oauth2/**", "/h2-console/*", "/oauth/callback").permitAll()
                .and()
                .formLogin().and()
                .httpBasic();
    }
}
