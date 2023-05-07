package com.specialteam.coffeshop.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.specialteam.coffeshop.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
