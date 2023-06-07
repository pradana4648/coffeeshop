package com.specialteam.coffeeshop.util;

import com.specialteam.coffeeshop.product.model.ProductImage;
import java.util.Base64;
import java.util.List;

public class AppUtils {
    public static String productImageAsBase64(ProductImage image) {
        StringBuilder builder = new StringBuilder();
        builder.append("data:");
        builder.append(image.getMimeType());
        builder.append(";base64,");
        builder.append(Base64.getEncoder().encodeToString(image.getContent().getData()));
        return builder.toString();
    }

    public static String productIdGenerator(String name) {
        List<String> values = List.of(name.split(" ")).stream().map(value -> value.substring(0)).toList();
        return String.join("", values).toUpperCase();
    }
}
