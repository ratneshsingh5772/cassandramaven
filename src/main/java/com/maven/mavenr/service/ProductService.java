package com.maven.mavenr.service;

import com.maven.mavenr.model.Product;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;

@Service
public class ProductService {

    private Map<String, Product> productData = new HashMap<>();

    // Save Product
    public Mono<Product> save(Product product) {
        productData.put(product.getId(), product);
        return Mono.just(product);
    }

    // Get All Products
    public Flux<Product> getAll() {
        return Flux.fromIterable(productData.values());
    }

    // Get Single Product
    public Mono<Product> getById(String id) {
        Product product = productData.get(id);
        return product != null ? Mono.just(product) : Mono.empty();
    }

    // Delete Product
    public Mono<Void> deleteById(String id) {
        productData.remove(id);
        return Mono.empty();
    }
}
