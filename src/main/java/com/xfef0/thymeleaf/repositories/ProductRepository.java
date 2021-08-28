package com.xfef0.thymeleaf.repositories;

import com.xfef0.thymeleaf.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
