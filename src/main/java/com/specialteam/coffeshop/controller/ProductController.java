package com.specialteam.coffeshop.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.specialteam.coffeshop.response.ProductResponse;
import com.specialteam.coffeshop.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ProductController implements ErrorController {
    @Autowired
    private ProductService service;

    /**
     * Custom error page for HTML only
     * 
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/error")
    public ModelAndView handleError(ModelMap model, HttpServletRequest request,
            HttpServletResponse response, Exception exception) {

        model.addAttribute("errorMessage", "Error");
        return new ModelAndView("error.html", model);
    }

    /**
     * Get List of Product
     * 
     * @param model
     * @return
     */
    @GetMapping(value = "/products", produces = { MediaType.TEXT_HTML_VALUE })
    public ModelAndView productView(ModelMap model) {
        model.addAttribute("products", service.getProducts());
        return new ModelAndView("product/index.html", model);
    }

    /**
     * Product CRUD
     * 
     * @param model
     * @param requestType
     * @return
     */
    @GetMapping(value = "/products/{requestType}", produces = {
            MediaType.TEXT_HTML_VALUE })
    public ModelAndView productView(ModelMap model,
            @PathVariable(name = "requestType") String requestType) {
        model.addAttribute("isAdmin", Boolean.TRUE);
        model.addAttribute("name", "Bujank");
        log.info("product/requestType ======> {}", requestType);

        if (requestType.equalsIgnoreCase("add")) {
            return new ModelAndView("product/add.html", model);
        } else if (requestType.equalsIgnoreCase("edit")) {
            return new ModelAndView("product/edit.html", model);
        } else {
            return new ModelAndView("product/delete.html", model);
        }
    }

    @PostMapping(value = "/api/v1/products/add", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @ResponseBody
    public ResponseEntity<Map<String, Object>> addProduct(@RequestParam("productRequest") String request,
            @RequestParam(value = "image", required = false) MultipartFile file) throws Exception {
        return service.addProduct(request, file);
    }

    @GetMapping(value = "/api/v1/products")
    @ResponseBody
    public ResponseEntity<List<ProductResponse>> getProducts() {
        return service.getProductsResponse();
    }

}
