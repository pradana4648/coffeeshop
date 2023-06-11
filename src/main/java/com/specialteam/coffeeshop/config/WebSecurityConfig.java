package com.specialteam.coffeeshop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.specialteam.coffeeshop.user.service.UserService;

@Configuration
public class WebSecurityConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests(
                        (request) -> {
                            request.requestMatchers("/api/v1/auth/register").permitAll();
                            request.requestMatchers("/api/v1/auth/check").permitAll();
                            request.requestMatchers("/api/v1/products/**").hasAuthority("USER");
                        })
                .authorizeHttpRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                // .exceptionHandling(ex -> ex.accessDeniedHandler(accessDeniedHandler)
                // .authenticationEntryPoint(authenticationEntryPoint))
                .build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(passwordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }
}
