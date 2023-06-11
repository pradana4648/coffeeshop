package com.specialteam.coffeeshop.product.repository;

import com.specialteam.coffeeshop.product.model.Cart;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<Cart, String> {
    List<Cart> findByUserId(String userId);
}
