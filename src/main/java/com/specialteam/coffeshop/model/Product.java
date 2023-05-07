package com.specialteam.coffeshop.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document("products")
@Data
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private Double price;
    private ProductImage image;
    private Integer quantity;

}
