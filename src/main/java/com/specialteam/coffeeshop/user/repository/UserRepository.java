package com.specialteam.coffeeshop.user.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.specialteam.coffeeshop.user.entity.User;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
}
