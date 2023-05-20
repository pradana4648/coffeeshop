package com.specialteam.coffeshop.util;

import java.util.Base64;

import com.specialteam.coffeshop.model.ProductImage;

public class ProductUtil {
    public static String productImageAsBase64(ProductImage image) {
        return Base64.getEncoder().encodeToString(image.getContent().getData());
    }
}
