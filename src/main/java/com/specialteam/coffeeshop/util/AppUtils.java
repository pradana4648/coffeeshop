package com.specialteam.coffeeshop.util;

import java.util.Base64;

import org.apache.commons.lang3.StringUtils;

import com.specialteam.coffeeshop.product.model.ProductImage;

public class AppUtils {
    public static String productImageAsBase64(ProductImage image) {
        StringBuilder builder = new StringBuilder();
        builder.append("data:");
        builder.append(image.getMimeType());
        builder.append(";base64,");
        builder.append(Base64.getEncoder().encodeToString(image.getContent().getData()));
        return builder.toString();
    }

    public static String concatFullname(String firstname, String lastname) {
        String format = StringUtils.joinWith(" ", new Object[] { firstname, lastname });
        return format;
    }
}
