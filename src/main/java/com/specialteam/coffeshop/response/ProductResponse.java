package com.specialteam.coffeshop.response;

import java.io.Serializable;

import lombok.Data;

@Data
public class ProductResponse implements Serializable {
    private String name;
    private String description;
    private Double price;

    private String mimeType;
    // Convert as Base64
    private String image;
}
