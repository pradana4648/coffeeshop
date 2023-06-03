package com.specialteam.coffeshop.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.specialteam.coffeshop.product.service.ProductService;

@Controller
public class HtmlProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ModelAndView main(ModelMap map) {
        map.put("products", productService.getProducts());
        return new ModelAndView("index", map);
    }
}
