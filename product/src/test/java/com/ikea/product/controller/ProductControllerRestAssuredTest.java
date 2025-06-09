package com.ikea.product.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ProductControllerRestAssuredTest {

    @Container
    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

    @BeforeAll
    static void setup() {
        // Start TestContainers MongoDB
        mongoDBContainer.start();

        // Set Spring Boot to use the TestContainer's MongoDB instance
        System.setProperty("spring.data.mongodb.uri", mongoDBContainer.getReplicaSetUrl());

        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @Test
    void testCreateProduct() {
        String jsonBody = """
                {
                  "id": "6",
                  "name": "LANDSKRONA-US",
                  "productType": { "id": "1", "name": "sofa" },
                  "colours": [
                    { "id": "1", "name": "Blue" },
                    { "id": "2", "name": "Ruby" }
                  ]
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .post("/api/products")
                .then()
                .statusCode(201)
                .body("id", equalTo("6"))
                .body("name", equalTo("LANDSKRONA-US"))
                .body("colours.size()", equalTo(2))
                .body("colours[0].name", equalTo("Blue"))
                .body("colours[1].name", equalTo("Ruby"));
    }

    @Test
    void testGetAllProductsWithPagination() {
        given()
                .queryParam("page", 0)
                .queryParam("size", 5)
                .queryParam("sortBy", "createdAt")
                .queryParam("sortDirection", "desc")
                .when()
                .get("/api/products")
                .then()
                .statusCode(anyOf(is(200), is(204)))
                .body("size()", lessThanOrEqualTo(5));
    }

    @Test
    void testGetProductById() {
        given()
                .when()
                .get("/api/products/10006")
                .then()
                .statusCode(200)
                .body("name", equalTo("LACK"))
                .body("productType.name", equalTo("Table"));
    }

    @Test
    void testGetNonexistentProduct() {
        given()
                .when()
                .get("/api/products/9999")
                .then()
                .statusCode(404);
    }
}