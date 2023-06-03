package com.specialteam.coffeshop.product.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.specialteam.coffeshop.product.model.Cart;

public interface CartRepository extends MongoRepository<Cart, String> {
    List<Cart> findByUserId(String userId);

}
