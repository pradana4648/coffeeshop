package com.specialteam.coffeshop.product.request;

import lombok.Data;

/**
 * Product Request Body
 */
@Data
public class ProductRequest {
    private String name;
    private String description;
    private Double price;

}
