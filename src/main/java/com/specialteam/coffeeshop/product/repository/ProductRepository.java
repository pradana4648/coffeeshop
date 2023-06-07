package com.specialteam.coffeeshop.product.repository;

import com.specialteam.coffeeshop.product.model.Product;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<Product> findByName(String name);
}
