package com.sample.ratingo.rest.controller;

import com.sample.ratingo.api.ProductDO;
import com.sample.ratingo.rest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductRestController {

    private final ProductService productService;

    @Autowired
    public ProductRestController(final ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity createNewProduct(@RequestBody ProductDO requestBody) {
        try {
            return ResponseEntity.accepted().body(productService.createProduct(requestBody));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity getProduct(@RequestParam("id") Long id) {
        try {
            return ResponseEntity.accepted().body(productService.getProduct(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
