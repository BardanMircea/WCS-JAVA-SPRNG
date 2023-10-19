package com.wildcodeschool.appWithSpringSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

public class SecurityConfiguration{

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/content").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/login").permitAll()
                );
        return http.build();
    }
}
