package com.specialteam.coffeshop.util;

import java.util.Base64;

import com.specialteam.coffeshop.product.model.ProductImage;

public class AppUtils {
    public static String productImageAsBase64(ProductImage image) {
        return Base64.getEncoder().encodeToString(image.getContent().getData());
    }
}
