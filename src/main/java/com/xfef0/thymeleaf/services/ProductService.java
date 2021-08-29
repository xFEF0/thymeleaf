package com.xfef0.thymeleaf.services;

import com.xfef0.thymeleaf.domain.Product;

import java.util.Optional;

public interface ProductService {
    Product saveProduct(Product product);
    Optional<Product> getProductById(Integer id);
    Iterable<Product> listAllProducts();
    void deleteProduct(Integer id);
}
