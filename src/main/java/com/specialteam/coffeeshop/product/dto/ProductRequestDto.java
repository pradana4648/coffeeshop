package com.specialteam.coffeeshop.product.dto;

import lombok.Data;

/** Product Request Body */
@Data
public class ProductRequestDto {
    private String name;
    private String description;
    private Double price;
}
