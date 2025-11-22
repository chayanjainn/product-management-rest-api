package com.crudapi.repository;


import com.crudapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findByName(String name);
}

