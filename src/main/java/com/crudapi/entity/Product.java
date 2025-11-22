package com.crudapi.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;


@Entity
@Table(name = "PRODUCT_TBL")
public class Product {

    @Id
    @GeneratedValue
    private int id;
    
    @NotBlank(message = "{NotBlank.product.name}")
    @Size(min = 2, max = 100, message = "{Size.product.name}")
    private String name;
    
    @NotBlank(message = "{NotBlank.product.description}")
    @Size(min = 5, max = 500, message = "{Size.product.description}")
    private String description;
    
    @DecimalMin(value = "0.01", message = "{DecimalMin.product.price}")
    @NotNull(message = "{NotNull.product.price}")
    private BigDecimal price;

    public Product() {}

    public Product(int id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
}