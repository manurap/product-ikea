package com.ikea.product.repository;

import com.ikea.product.model.Color;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ColorRepository extends MongoRepository<Color, String> {}