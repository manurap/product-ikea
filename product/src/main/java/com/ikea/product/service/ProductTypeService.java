package com.ikea.product.service;

import com.ikea.product.model.ProductType;
import com.ikea.product.repository.ProductTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductTypeService {

    private final ProductTypeRepository productTypeRepository;

    public ProductTypeService(ProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    // Find all product types
    public List<ProductType> getAllProductTypes() {
        return productTypeRepository.findAll();
    }

    // Find product type by ID
    public Optional<ProductType> getProductTypeById(String id) {
        return productTypeRepository.findById(id);
    }

    // Add a new product type
    public ProductType addProductType(ProductType productType) {
        return productTypeRepository.save(productType);
    }

    public void deleteAllProductTypes() {
        productTypeRepository.deleteAll();
    }
}