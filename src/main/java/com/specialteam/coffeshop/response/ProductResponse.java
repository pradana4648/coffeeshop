package com.specialteam.coffeshop.response;

import lombok.Data;

@Data
public class ProductResponse<T> {
    private String status;
    private Boolean error;
    private T results;
}
