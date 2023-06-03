package com.specialteam.coffeshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.specialteam.coffeshop.product.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HtmlController {
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ModelAndView main(ModelMap map, HttpServletRequest request) {
        map.put("products", productService.getProducts());
        map.put("user", request.getUserPrincipal().getName());
        map.put("cartCount", productService.totalProductInCarts(request.getUserPrincipal().getName()));
        return new ModelAndView("index", map);
    }
}
