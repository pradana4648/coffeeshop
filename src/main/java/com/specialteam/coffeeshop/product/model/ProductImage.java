package com.specialteam.coffeeshop.product.model;

import lombok.Data;
import org.bson.types.Binary;

@Data
public class ProductImage {
    private String filename;
    private String mimeType;
    private Binary content;
}
