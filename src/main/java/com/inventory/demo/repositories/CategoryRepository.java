package com.inventory.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.demo.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

	
}
