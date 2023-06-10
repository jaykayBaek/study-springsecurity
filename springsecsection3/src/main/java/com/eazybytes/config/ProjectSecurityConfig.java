package com.eazybytes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfig {
    /**
     * Security를 적용할 URL 설정을 해당 FilterChain으로 설정할 수 있다.
     * SecurityFilterChain은 스프링 빈으로 관리된다.
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf((csrf) -> csrf.disable())
                        .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/myAccount", "/myBalance", "/myLoans", "/myCards").authenticated()
                        .requestMatchers("/notices", "/contact", "/register").permitAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    /**
     * InMemoryUserDetailsManager
     * @return
     */
    /*@Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        *//*접근법 2. 사용자 세부 정보를 생성하는 동안
        withDefaultPasswordEncoder() 메소드를 사용하는 접근법*//*
        *//*UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("1234")
                .authorities("admin")
                .build();
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("1234")
                .authorities("read")
                .build();

        return new InMemoryUserDetailsManager(admin, user);*//*

        *//*접근법 2. 사용자 세부 정보를 생성하는 동안
        NoOpPasswordEncoder Bean을 등록하는 접근법*//*
        UserDetails admin = User.withUsername("admin")
                .password("1234")
                .authorities("admin")
                .build();
        UserDetails user = User.withUsername("user")
                .password("1234")
                .authorities("read")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }*/

    /*@Bean
    public UserDetailsService userDetailsService(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }*/

    /**
     * NoOpPasswordEncoder는 프로덕션에 사용할 것을 권장하지 않는다.
     * 프로덕션 코드가 아닌 경우에만 권장
     *
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

}
