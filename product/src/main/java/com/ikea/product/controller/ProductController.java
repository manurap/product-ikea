package com.ikea.product.controller;

import com.ikea.product.exception.ProductNotFoundException;
import com.ikea.product.model.Product;
import com.ikea.product.model.ProductResponse;
import com.ikea.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Product API", description = "Operations for managing products")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @Operation(summary = "Add a new product", description = "Creates and saves a new product")
    public ResponseEntity<?> addProduct(@Valid @RequestBody Product product) {
            Product savedProduct = productService.addProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);

    }

    @GetMapping
    @Operation(summary = "Retrieve paginated products", description = "Fetches a paginated list of products based on sorting criteria")
    public ResponseEntity<Page<ProductResponse>> getProducts(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "5") int size,
                                                             @RequestParam(defaultValue = "createdAt") String sortBy,
                                                             @RequestParam(defaultValue = "desc") String sortDirection) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection), sortBy));
        Page<ProductResponse> products = productService.getAllProducts(pageable)
                .map(product -> new ProductResponse(product.id(), product.name()));

        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(products); // 204 No Content
        }

        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find product by ID", description = "Retrieves a product by its unique identifier")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        try {
            Product product = productService.getProductById(id)
                    .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));
            return ResponseEntity.ok(product);
        } catch (ProductNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}