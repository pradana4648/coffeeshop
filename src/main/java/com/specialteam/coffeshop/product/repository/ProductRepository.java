package com.specialteam.coffeshop.product.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.specialteam.coffeshop.product.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<Product> findByName(String name);
}
