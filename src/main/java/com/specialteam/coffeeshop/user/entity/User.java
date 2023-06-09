package com.specialteam.coffeeshop.user.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document("users")
@Data
public class User {
    @Id
    private String uid;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private List<String> roles;
    private Boolean isAccountNonExpired;
    private Boolean isAccountNonLocked;
    private Boolean isEnabled;
    private Boolean isCredentialsNonExpired;
    private Address userAddress;

}
