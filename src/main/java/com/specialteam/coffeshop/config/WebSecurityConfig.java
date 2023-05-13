package com.specialteam.coffeshop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.specialteam.coffeshop.service.MyUserService;

import jakarta.annotation.Resource;

@Configuration
public class WebSecurityConfig {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource
    private MyUserService service;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests()
                .requestMatchers("/", "/products", "/api/v1/register").permitAll()
                .requestMatchers("/static/**", "/webjars/**").permitAll()
                .anyRequest().authenticated().and().authenticationProvider(daoAuthenticationProvider());
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(passwordEncoder);
        provider.setUserDetailsService(service);
        return provider;
    }

}
