package com.specialteam.coffeshop.controller.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.specialteam.coffeshop.controller.ProductController;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice(basePackageClasses = ProductController.class)
@Slf4j
public class ProductControllerAdvice extends ResponseEntityExceptionHandler {
    @Override
    @Nullable
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.error("HEADERS : {}", headers);
        log.error("HTTP STATUS : {}", status);
        log.error("HTTP REQUEST : {}", request);
        log.error("EXCEPTION RAISE : {}", ex);
        return super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
    }
}
