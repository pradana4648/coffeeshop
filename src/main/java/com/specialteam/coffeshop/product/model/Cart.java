package com.specialteam.coffeshop.product.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import nonapi.io.github.classgraph.json.Id;

@Document("carts")
@Data
public class Cart {
    @Id
    private String cartId;

    private String userId;

    private List<Product> products;
}
