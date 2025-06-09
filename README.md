# 🏢 Product-Ikea

## 📁 Project Structure
This repository contains two main folders: prdocut and product-app


1. **`product` (Spring Boot Microservice)**
   - A backend microservice handling product data and business logic.
   - Uses MongoDB as the database.
   - Exposes RESTful APIs for the frontend to interact with.
   - Integrated with TestContainers for isolated testing.
2. **`product-app` (React Frontend)**
   - A React-based web application for managing products.
   - Communicates with the Spring Boot microservice (`product`) via REST APIs.


## 🚀 Getting Started -Product-Backend

### ⚙️ Backend Prerequisites
Before running the application, ensure the following dependencies are installed:

- **Java 17+** (Required for Spring Boot)
- **Maven** (Build tool for backend)
- **Docker** (Used for TestContainers and MongoDB)

### 🚀 **Setup & Run**
**Build with Maven:**
```sh
cd product
mvn clean package
```

**Build Docker image:**
```sh
docker build -t product-service .
```

**Start services:**
```sh
docker-compose down && docker-compose up -d
```
**Make sure MongoDB is running before starting the Spring Boot application.**
---

MongoDB  UI will be available at [http://localhost:8081/](http://localhost:8081/)  
Default user: `admin`  
Default password: `pass`

---

API will be available at http://localhost:8080/api/products.

## API Documentation

Swagger UI is available at:  
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---
## API Usage

### Product

- **Get product by ID**
    ```sh
    curl -X GET http://localhost:8080/api/products/3432
    ```
- **Add a product**
    ```sh
    curl -X POST "http://localhost:8080/api/products" \
    -H "Content-Type: application/json" \
    -d "{\"id\":\"4\",\"name\":\"LANDSKRONA-CAN\",\"productType\":{\"id\":\"1\",\"name\":\"Sofa\"},\"colours\":[{\"id\":\"1\",\"name\":\"Blue\"},{\"id\":\"2\",\"name\":\"Red\"}]}"
    ```

- **Get paginated products**
    ```sh
    curl -X GET "http://localhost:8080/api/products?page=0&size=2"
    ```
### Color

- **Get color by ID**
    ```sh
    curl -X GET "http://localhost:8080/api/colors/1"
    ```

- **Get all colors**
    ```sh
    curl -X GET "http://localhost:8080/api/colors"
    ```

- **Add a color**
    ```sh
    curl -X POST "http://localhost:8080/api/colors" \
    -H "Content-Type: application/json" \
    -d '{
    "id": "1",
    "name": "Blue"
    }'
    ```
### Product Type

- **Add a product type**
    ```sh
    curl -X POST "http://localhost:8080/api/product-types" \
    -H "Content-Type: application/json" \
    -d '{
    "id": "101",
    "name": "Furniture"
    }'
    ```

- **Get all product types**
    ```sh
    curl -X GET "http://localhost:8080/api/product-types"
    ```
- **Get product type by ID**
    ```sh
    curl -X GET "http://localhost:8080/api/product-types/101"
    ```

---




## 🚀 Getting Started -Product-app (Front-end)

### Prerequisites

- **Node.js** (v16+ recommended)
- **npm** (comes with Node.js) or **yarn**
- The backend (`product` Spring Boot service) should be running at `http://localhost:8080`

---

### Setup & Run

1. **Clone the repository and navigate to the frontend folder:**
    ```sh
    cd product-app
    ```
2. **Install dependencies:**
    ```sh
    npm install
    ```
    or
    ```sh
    yarn
    ```
 3. **Start the development server:**
    ```sh
    npm start
    ```
    or
    ```sh
    yarn start
    ```

4. **Open your browser and visit:**  
   [http://localhost:3000](http://localhost:3000)

   ---

## 🌟 Features

- **Product List:**  
  View a paginated list of all products with ID, name, and type.

- **Add Product:**  
  Add a new product, select its type, and assign multiple colors.

- **Find Product:**  
  Search for a product by ID and view all its details.

---

## ⚙️ Configuration

- The API base URL is set to `http://localhost:8080` by default.
- You can change the API URL in `src/const/productConst.js` if your backend runs elsewhere.

---
## 📁 Project Structure

```
product-app/
├── public/
├── src/
│   ├── components/
│   │   ├── ProductList.js
│   │   ├── ProductForm.js
│   │   └── FindProduct.js
│   ├── services/
│   │   └── ProductService.js
│   ├── const/
│   │   └── productConst.js
│   ├── App.js
│   └── index.js
├── package.json
└── README.md
```
--## 📝 Notes

- Make sure the backend API is running before starting the frontend.
- The app uses [react-router-dom](https://reactrouter.com/) for navigation.
- All API errors and validation messages are shown in the UI.

---

## 📚 Useful Scripts

- `npm start` — Start the development server
- `npm run build` — Build the app for production
- `npm test` — Run tests (if available)

---

