# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.5.0/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.5.0/maven-plugin/build-image.html)
* [Spring Web](https://docs.spring.io/spring-boot/3.5.0/reference/web/servlet.html)
* [Spring Data MongoDB](https://docs.spring.io/spring-boot/3.5.0/reference/data/nosql.html#data.nosql.mongodb)
* [Docker Compose Support](https://docs.spring.io/spring-boot/3.5.0/reference/features/dev-services.html#features.dev-services.docker-compose)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with MongoDB](https://spring.io/guides/gs/accessing-data-mongodb/)

### Docker Compose support
This project contains a Docker Compose file named `compose.yaml`.
In this file, the following services have been defined:

* mongodb: [`mongo:latest`](https://hub.docker.com/_/mongo)

Please review the tags of the used images and set them to the same as you're running in production.

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

docker-compose down && docker-compose up -d

MongoExpress - UI for mongoDB
http://localhost:8081/
user:admin
pwd:pass

--product

curl -X GET http://localhost:8080/api/products/3432

curl -X POST "http://localhost:8080/api/products" \
-H "Content-Type: application/json" \
-d "{\"id\":\"4\",\"name\":\"LANDSKRONA-CAN\",\"productType\":{\"id\":\"1\",\"name\":\"Sofa\"},\"colours\":[{\"id\":\"1\",\"name\":\"Blue\"},{\"id\":\"2\",\"name\":\"Red\"}]}"

curl -X GET "http://localhost:8080/api/products?page=0&size=2"


--color
curl -X GET "http://localhost:8080/api/colors/1"

curl -X GET "http://localhost:8080/api/colors"

curl -X POST "http://localhost:8080/api/colors" \
-H "Content-Type: application/json" \
-d '{
"id": "1",
"name": "Blue"
}'

--product type
curl -X POST "http://localhost:8080/api/product-types" \
-H "Content-Type: application/json" \
-d '{
"id": "101",
"name": "Furniture"
}'


curl -X GET "http://localhost:8080/api/product-types"

curl -X GET "http://localhost:8080/api/product-types/101"


http://localhost:8080/swagger-ui/index.html

---build
mvn clean package
docker build -t product-service .
docker-compose down && docker-compose up