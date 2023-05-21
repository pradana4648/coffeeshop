package com.specialteam.coffeshop.util;

import java.util.Base64;

import com.specialteam.coffeshop.product.model.ProductImage;

public class AppUtils {
    public static String productImageAsBase64(ProductImage image) {
        StringBuilder builder = new StringBuilder();
        builder.append("data:");
        builder.append(image.getMimeType());
        builder.append(";base64,");
        builder.append(Base64.getEncoder().encodeToString(image.getContent().getData()));
        return builder.toString();
    }
}
