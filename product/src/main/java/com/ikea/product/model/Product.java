package com.ikea.product.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "products")
public record Product(
        @NotBlank String id,
        @Size(min = 3, max = 50, message = "Product Name must be between 3 and 50 characters")
        String name,
        @NotNull ProductType productType,
        List<Color> colours,
        @Field("createdAt") LocalDateTime createdAt
) {
    @JsonCreator
    public Product(String id, String name, ProductType productType, List<Color> colours) {
        this(id, name, productType, colours, LocalDateTime.now());
    }
}
