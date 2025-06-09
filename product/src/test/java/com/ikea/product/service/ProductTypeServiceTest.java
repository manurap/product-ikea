package com.ikea.product.service;

import com.ikea.product.config.TestMongoConfig;
import com.ikea.product.model.ProductType;
import com.ikea.product.repository.ProductTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest
@Import(TestMongoConfig.class)
class ProductTypeServiceTest {

    @Container
    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

    @DynamicPropertySource
    static void mongoProperties(org.springframework.test.context.DynamicPropertyRegistry registry) {
        mongoDBContainer.start();
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private ProductTypeService productTypeService;

    @BeforeEach
    void cleanDatabase() {
        productTypeRepository.deleteAll();
    }

    @Test
    void shouldAddProductTypeSuccessfully() {
        ProductType productType = new ProductType("123", "Furniture");
        productTypeService.addProductType(productType);

        Optional<ProductType> fetchedProductType = productTypeService.getProductTypeById("123");
        assertTrue(fetchedProductType.isPresent());
        assertEquals("Furniture", fetchedProductType.get().name());
    }

    @Test
    void shouldReturnAllProductTypes() {
        ProductType productType1 = new ProductType("101", "Electronics");
        ProductType productType2 = new ProductType("102", "Clothing");

        productTypeService.addProductType(productType1);
        productTypeService.addProductType(productType2);

        List<ProductType> productTypes = productTypeService.getAllProductTypes();
        assertFalse(productTypes.isEmpty());
        assertEquals(2, productTypes.size());
    }
}