package com.inventory.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.demo.model.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer>{
	
}
