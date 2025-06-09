package com.ikea.product.controller;

import com.ikea.product.model.ProductType;
import com.ikea.product.service.ProductTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-types")
@Tag(name = "Product Type API", description = "Operations for managing product types")
public class ProductTypeController {

    private final ProductTypeService productTypeService;

    public ProductTypeController(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    //  Find all product types
    @GetMapping
    @Operation(summary = "Find all product types", description = "Retrieves all available product types from the database")
    public ResponseEntity<List<ProductType>> findAll() {
        return ResponseEntity.ok(productTypeService.getAllProductTypes());
    }

    //  Find product type by ID
    @GetMapping("/{id}")
    @Operation(summary = "Find product type by ID", description = "Retrieves a product type by its unique ID")
    public ResponseEntity<ProductType> findById(@PathVariable String id) {
        return productTypeService.getProductTypeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Add a new product type
    @PostMapping
    @Operation(summary = "Add a new product type", description = "Creates and saves a new product type in the database")
    public ResponseEntity<ProductType> addProductType(@RequestBody ProductType productType) {
        ProductType savedProductType = productTypeService.addProductType(productType);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProductType);
    }
}