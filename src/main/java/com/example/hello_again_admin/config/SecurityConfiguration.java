package com.example.hello_again_admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authz) -> authz.requestMatchers("/**")
            .permitAll()
        ).authorizeHttpRequests((authz) -> authz.requestMatchers("/admin/**")
            .hasRole("ADMIN")
            .anyRequest().authenticated()
        );
        // http.authorizeHttpRequests((authz) -> authz
        // .requestMatchers("/admin/**")
        // .hasRole("ADMIN")
        // .anyRequest()
        // .authenticated());
        return http.build();
    }
}