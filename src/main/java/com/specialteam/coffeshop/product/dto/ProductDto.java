package com.specialteam.coffeshop.product.dto;

import lombok.Data;

@Data
public class ProductDto {
    private String id;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private String imageAsBase64;
    private String mimeType;
    private String filename;
}
