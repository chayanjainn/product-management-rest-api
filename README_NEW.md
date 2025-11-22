# Product Management REST API

A RESTful API built with Spring Boot for Product CRUD operations featuring Bean Validation, Global Exception Handling, Swagger Documentation, MySQL persistence, and Basic Authentication.

## Features

- **CRUD Operations**: Create, Read, Update, Delete products
- **Bean Validation**: Input validation with custom error messages
- **Global Exception Handling**: Standardized error responses
- **Swagger Documentation**: Interactive API documentation
- **MySQL Database**: Relational database persistence
- **Spring Security**: Basic authentication

## Key Requirements

- **Java**: 17+
- **Spring Boot**: 3.5.7
- **Maven**: 3.9+
- **MySQL**: 8.0+

## Complete Setup Steps

### 1. Prerequisites Installation
- Install Java 17 or higher
- Install Maven 3.9 or higher
- Install MySQL 8.0 or higher
- Ensure MySQL service is running

### 2. Database Setup with SQL Commands
```sql
-- Connect to MySQL as root
mysql -u root -p

-- Create database
CREATE DATABASE chayandb;

-- Create user (optional)
CREATE USER 'productuser'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON chayandb.* TO 'productuser'@'localhost';
FLUSH PRIVILEGES;
```

### 3. Configuration Details
Update `src/main/resources/application.properties`:
```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/chayandb?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=chayan@123
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA Configuration
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# SpringDoc OpenAPI Configuration
springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.swagger-ui.operationsSorter=method
springdoc.swagger-ui.tagsSorter=alpha
springdoc.swagger-ui.tryItOutEnabled=true

# Security Configuration
spring.security.user.name=admin
spring.security.user.password=password
spring.security.user.roles=USER
```

### 4. Build and Run Instructions
```bash
# Navigate to project directory
cd product-management-rest-api

# Clean and build the project
mvn clean install

# Run the application
mvn spring-boot:run

# Alternative: Run the JAR file
java -jar target/productmgmnt-0.0.1-SNAPSHOT.jar
```

### 5. Verification Steps
- Application starts on: http://localhost:8080
- Swagger UI: http://localhost:8080/swagger-ui.html
- API Docs: http://localhost:8080/v3/api-docs

## All API Endpoints

### Authentication Details
All endpoints require Basic Authentication:
- **Username**: admin
- **Password**: password

### Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| POST | `/products` | Create single product | Yes |
| POST | `/addProducts` | Create multiple products | Yes |
| GET | `/products` | Get all products | Yes |
| GET | `/products/{id}` | Get product by ID | Yes |
| PUT | `/products/{id}` | Update product | Yes |
| DELETE | `/products/{id}` | Delete product | Yes |

## Sample Requests

### 1. Create Product
```bash
POST http://localhost:8080/products
Authorization: Basic YWRtaW46YWRtaW4xMjM=
Content-Type: application/json

{
    "name": "Laptop",
    "description": "High-performance gaming laptop",
    "price": 1299.99
}
```

### 2. Create Multiple Products
```bash
POST http://localhost:8080/addProducts
Authorization: Basic YWRtaW46YWRtaW4xMjM=
Content-Type: application/json

[
    {
        "name": "Laptop",
        "description": "Gaming laptop",
        "price": 1299.99
    },
    {
        "name": "Mouse",
        "description": "Wireless mouse",
        "price": 29.99
    }
]
```

### 3. Get All Products
```bash
GET http://localhost:8080/products
Authorization: Basic YWRtaW46YWRtaW4xMjM=
```

### 4. Get Product by ID
```bash
GET http://localhost:8080/products/1
Authorization: Basic YWRtaW46YWRtaW4xMjM=
```

### 5. Update Product
```bash
PUT http://localhost:8080/products/1
Authorization: Basic YWRtaW46YWRtaW4xMjM=
Content-Type: application/json

{
    "name": "Updated Laptop",
    "description": "Updated high-performance gaming laptop",
    "price": 1399.99
}
```

### 6. Delete Product
```bash
DELETE http://localhost:8080/products/1
Authorization: Basic YWRtaW46YWRtaW4xMjM=
```

## Validation Rules

- **Name**: Required, 2-100 characters
- **Description**: Required, 5-500 characters  
- **Price**: Required, must be greater than 0.01

## Error Responses

### Validation Error (400)
```json
{
    "timestamp": "2024-11-21T10:30:00",
    "status": 400,
    "error": "Validation Failed",
    "message": "Input validation failed for one or more fields",
    "path": "/products",
    "validationErrors": {
        "name": "Product name is required and cannot be empty",
        "description": "Product description is required and cannot be empty",
        "price": "Price is required"
    }
}
```

### Not Found Error (404)
```json
{
    "timestamp": "2024-11-21T10:30:00",
    "status": 404,
    "error": "Resource Not Found",
    "message": "Product not found with id: 999",
    "path": "/products/999"
}
```

## Testing Instructions

### Swagger UI Usage
1. Open http://localhost:8080/swagger-ui.html
2. Click "Authorize" button
3. Enter credentials: admin / password
4. Test all endpoints interactively

### Postman Setup
1. Import the API collection
2. Set Authorization: Basic Auth (admin / password)
3. Test each endpoint with sample data

### cURL Examples
```bash
# Create product
curl -X POST http://localhost:8080/products \
  -H "Content-Type: application/json" \
  -H "Authorization: Basic YWRtaW46YWRtaW4xMjM=" \
  -d '{"name":"Laptop","description":"Gaming laptop","price":1299.99}'

# Get all products
curl -X GET http://localhost:8080/products \
  -H "Authorization: Basic YWRtaW46YWRtaW4xMjM="
```

## Troubleshooting Section

### Common Issues and Solutions

1. **MySQL Connection Error**
   - Ensure MySQL is running
   - Check database credentials in application.properties
   - Verify database exists

2. **Port Already in Use**
   - Change server.port in application.properties
   - Kill process using port 8080

3. **Authentication Failed**
   - Use correct credentials: admin/password
   - Ensure Authorization header is set

4. **Validation Errors**
   - Name: 2-100 characters, required
   - Description: 5-500 characters, required
   - Price: > 0.01, required

## Quick Start Commands

```bash
# 1. Start MySQL service
sudo systemctl start mysql  # Linux
brew services start mysql   # macOS
# Windows: Start MySQL from Services

# 2. Create database
mysql -u root -p -e "CREATE DATABASE chayandb;"

# 3. Run application
mvn spring-boot:run

# 4. Test API
curl -X GET http://localhost:8080/products -u admin:password
```

## Technologies Used

- **Spring Boot**: 3.5.7
- **Spring Data JPA**: Database operations
- **Spring Validation**: Input validation
- **Spring Security**: Basic authentication
- **MySQL Connector**: Database connectivity
- **SpringDoc OpenAPI**: Swagger documentation
- **Lombok**: Code generation
- **Maven**: Build tool
- **Java**: 17

## Project Structure

```
src/
├── main/
│   ├── java/com/fullstack/
│   │   ├── advice/           # Global exception handling
│   │   ├── config/           # Configuration classes
│   │   ├── controller/       # REST controllers
│   │   ├── dto/             # Data transfer objects
│   │   ├── entity/          # JPA entities
│   │   ├── exception/       # Custom exceptions
│   │   ├── repository/      # Data repositories
│   │   └── service/         # Business logic
│   └── resources/
│       ├── application.properties
│       └── messages.properties
```

## License

This project is licensed under the MIT License.