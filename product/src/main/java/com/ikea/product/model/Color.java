package com.ikea.product.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "colors")
public record Color(String id, String name) {}

