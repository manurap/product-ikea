package com.ikea.product.service;

import com.ikea.product.exception.DuplicateProductException;
import com.ikea.product.model.Product;
import com.ikea.product.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> getAllProducts(Pageable pageable) {
        logger.info("Fetching all products");
        return  productRepository.findAll(pageable);
    }

    public Optional<Product> getProductById(String id) {
        logger.info("Fetching product with ID: {}", id);
        return productRepository.findById(id);
    }

    public Product addProduct(Product product) {
        logger.info("Checking if product already exists: {}", product.id());

        Optional<Product> existingProduct = productRepository.findById(product.id());
        if (existingProduct.isPresent()) {
            logger.warn("Product already exists: {}", product.id());
            throw new DuplicateProductException("Product with id " + product.id() + " already exists.");
        }

        logger.info("Adding new product: {}", product.name());
        return productRepository.save(product);
    }

    public void deleteAllProducts() {
        logger.warn("Deleting all products from the database");
        productRepository.deleteAll();
    }


}