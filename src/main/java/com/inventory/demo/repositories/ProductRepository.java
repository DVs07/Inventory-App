package com.inventory.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.demo.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	
}
