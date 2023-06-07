package com.specialteam.coffeeshop.product.model;

import java.util.List;
import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("carts")
@Data
public class Cart {
    @Id
    private String cartId;

    private String userId;

    private List<Product> products;
}
