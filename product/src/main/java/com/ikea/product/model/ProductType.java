package com.ikea.product.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ProductTypes")
public record ProductType(String id, String name) {}


