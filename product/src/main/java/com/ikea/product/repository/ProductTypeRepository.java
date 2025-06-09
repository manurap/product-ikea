package com.ikea.product.repository;

import com.ikea.product.model.ProductType;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductTypeRepository extends MongoRepository<ProductType, String> {}
