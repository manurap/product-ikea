package com.ikea.product.service;

import com.ikea.product.config.TestMongoConfig;
import com.ikea.product.model.Color;
import com.ikea.product.repository.ColorRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest
@Import(TestMongoConfig.class)
class ColorServiceTest {

    @Container
    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private ColorService colorService;

    @BeforeAll
    static void setUp() {
        mongoDBContainer.start();
        System.setProperty("spring.data.mongodb.uri", mongoDBContainer.getReplicaSetUrl());
    }

    @Test
    void shouldAddColorSuccessfully() {
        Color color = new Color("123", "Blue");
        colorService.addColor(color);

        Optional<Color> fetchedColor = colorService.getColorById("123");
        assertTrue(fetchedColor.isPresent());
        assertEquals("Blue", fetchedColor.get().name());
    }

    @Test
    void shouldReturnAllColors() {
        colorService.deleteAllColors();
        Color color1 = new Color("101", "Red");
        Color color2 = new Color("102", "Green");

        colorService.addColor(color1);
        colorService.addColor(color2);

        List<Color> colors = colorService.getAllColors();
        assertFalse(colors.isEmpty());
        assertEquals(2, colors.size());
    }
}