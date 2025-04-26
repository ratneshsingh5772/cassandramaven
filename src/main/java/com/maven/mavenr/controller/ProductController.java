package com.maven.mavenr.controller;

import com.maven.mavenr.model.Product;
import com.maven.mavenr.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public Mono<Product> createProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @GetMapping
    public Flux<Product> getAllProducts() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<Product> getProductById(@PathVariable String id) {
        return productService.getById(id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteProduct(@PathVariable String id) {
        return productService.deleteById(id);
    }
}
