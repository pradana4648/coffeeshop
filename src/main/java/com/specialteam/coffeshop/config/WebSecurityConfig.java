package com.specialteam.coffeshop.config;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Bean(name = "filterChain")
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().ignoringRequestMatchers("/api/v1/register");
        http.authorizeHttpRequests().requestMatchers("/", "/products", "/api/v1/register").permitAll();
        http.authorizeHttpRequests().requestMatchers("/static/**", "/webjars/**").permitAll();
        http.authorizeHttpRequests().anyRequest().authenticated().and().formLogin(withDefaults())
                .httpBasic(withDefaults());
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
