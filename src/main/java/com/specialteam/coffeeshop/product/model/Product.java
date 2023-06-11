package com.specialteam.coffeeshop.product.model;

import java.util.Date;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("products")
@Data
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private Double price;
    private ProductImage image;
    @Field
    private Integer quantity = 1;
    @CreatedDate
    private Date createDate;
    @LastModifiedDate
    private Date modifiedDate;
    @Field
    private Boolean isAvailable = true;
}
