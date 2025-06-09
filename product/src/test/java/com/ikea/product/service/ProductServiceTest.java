package com.ikea.product.service;

import com.ikea.product.config.TestMongoConfig;
import com.ikea.product.model.Color;
import com.ikea.product.model.Product;
import com.ikea.product.model.ProductType;
import com.ikea.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Pageable;
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
class ProductServiceTest {

    @Container
    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

    @DynamicPropertySource
    static void mongoProperties(org.springframework.test.context.DynamicPropertyRegistry registry) {
        mongoDBContainer.start();
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @BeforeEach
    void cleanDatabase() {
        productRepository.deleteAll();
    }

    @Test
    void shouldAddProductSuccessfully() {
        Color red = new Color("1", "Red");
        Color blue = new Color("2", "Blue");
        ProductType sofa = new ProductType("100", "Sofa");
        Product product = new Product("3001", "SANDSBERG", sofa, List.of(red, blue));

        productService.addProduct(product);

        Optional<Product> fetchedProduct = productService.getProductById("3001");
        assertTrue(fetchedProduct.isPresent());
        assertEquals("SANDSBERG", fetchedProduct.get().name());
    }

    @Test
    void shouldReturnAllProducts() {
        Color red = new Color("1", "Red");
        Color blue = new Color("2", "Blue");
        ProductType sofa = new ProductType("100", "Sofa");
        Product product1 = new Product("101111", "Chair", sofa, List.of(red, blue));
        Product product2 = new Product("102222", "Lamp", sofa, List.of(red, blue));

        productService.addProduct(product1);
        productService.addProduct(product2);

        List<Product> products = productService.getAllProducts(Pageable.unpaged()).getContent();
        assertFalse(products.isEmpty());
        assertEquals(2, products.size());
    }

    @Test
    void shouldDeleteAllProducts() {
        productService.deleteAllProducts();
        List<Product> products = productService.getAllProducts(Pageable.unpaged()).getContent();
        assertTrue(products.isEmpty());
    }
}