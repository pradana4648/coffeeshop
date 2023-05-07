package com.specialteam.coffeshop.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/webjars/**", "/static/**");
    }

    @Bean(name = "filterChain")
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() throws Exception {
        List<UserDetails> users = new ArrayList<UserDetails>();

        users.add(User.withDefaultPasswordEncoder()
                .username("user")
                .password("12345678").roles("USER").build());
        return new InMemoryUserDetailsManager(users);
    }

    // @Bean(name = "filterChain")
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
    // Exception {
    // return http.build();
    // }
}
