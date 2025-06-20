package com.ikea.product.repository;

import com.ikea.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
    @Override
    Page<Product> findAll(Pageable pageable);
}
