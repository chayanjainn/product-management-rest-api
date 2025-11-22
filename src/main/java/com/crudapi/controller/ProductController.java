package com.crudapi.controller;


import com.crudapi.entity.Product;


import com.crudapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;

@RestController
@Tag(name = "Product Management", description = "APIs for managing products")
@SecurityRequirement(name = "basicAuth")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/products")
    @Operation(summary = "Create a new product", description = "Creates a new product with the provided details")
    @ApiResponse(responseCode = "200", description = "Product created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    public Product addProduct(@Valid @RequestBody Product product) {
        return service.saveProduct(product);
    }

    @PostMapping("/addProducts")
    public List<Product> addProducts(@RequestBody List<@Valid Product> products) {
        return service.saveProducts(products);
    }

    @GetMapping("/products")
    @Operation(summary = "Get all products", description = "Retrieves a list of all products")
    @ApiResponse(responseCode = "200", description = "Products retrieved successfully")
    public List<Product> findAllProducts() {
        return service.getProducts();
    }

    @GetMapping("/products/{id}")
    @Operation(summary = "Get product by ID", description = "Retrieves a specific product by its ID")
    @ApiResponse(responseCode = "200", description = "Product found")
    @ApiResponse(responseCode = "404", description = "Product not found")
    public Product findProductById(@PathVariable int id) {
        return service.getProductById(id);
    }



    @PutMapping("/products/{id}")
    @Operation(summary = "Update product", description = "Updates an existing product with new details")
    @ApiResponse(responseCode = "200", description = "Product updated successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    @ApiResponse(responseCode = "404", description = "Product not found")
    public Product updateProduct(@PathVariable int id, @Valid @RequestBody Product product) {
        product.setId(id);
        return service.updateProduct(product);
    }

    @DeleteMapping("/products/{id}")
    @Operation(summary = "Delete product", description = "Deletes a product by its ID")
    @ApiResponse(responseCode = "200", description = "Product deleted successfully")
    @ApiResponse(responseCode = "404", description = "Product not found")
    public String deleteProduct(@PathVariable int id) {
        return service.deleteProduct(id);
    }
}