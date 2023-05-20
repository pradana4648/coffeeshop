package com.specialteam.coffeshop.dto;

import lombok.Data;

@Data
public class ProductDto {
    private String name;
    private String description;
    private Double price;
    private String mimeType;
    private String image;
}
