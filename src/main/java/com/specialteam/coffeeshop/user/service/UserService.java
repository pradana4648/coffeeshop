package com.specialteam.coffeeshop.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.specialteam.coffeeshop.user.entity.User;
import com.specialteam.coffeeshop.user.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            User res = user.get();
            List<SimpleGrantedAuthority> authorities = res.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role)).toList();
            return new org.springframework.security.core.userdetails.User(res.getUsername(), res.getPassword(),
                    authorities);
        } else {
            throw new UsernameNotFoundException("Username not found");
        }
    }

    public String registerUser(Map<String, Object> body) throws Exception {
        try {
            // Check existed user
            Optional<User> getUserByUsername = userRepository.findByUsername((String) body.get("username"));
            if (getUserByUsername.isPresent()) {
                return "user already exist";
            }
            List<String> roles = new ArrayList<>();
            roles.add("USER");

            User user = new User();
            user.setUsername((String) body.get("username"));
            user.setFirstname((String) body.get("firstname"));
            user.setLastname((String) body.get("lastname"));
            user.setPassword(passwordEncoder.encode((String) body.get("password")));
            user.setIsAccountNonExpired(true);
            user.setIsAccountNonLocked(true);
            user.setIsEnabled(true);
            user.setIsCredentialsNonExpired(true);
            user.setRoles(roles);
            userRepository.save(user);
            return "success register user";

        } catch (Exception e) {
            throw e;
        }
    }
}
