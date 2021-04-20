package com.inventory.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.inventory.demo.model.Category;
import com.inventory.demo.repositories.CategoryRepository;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryRepository repo;
	
	@GetMapping("/categories")
	public String listCategories(Model model) {
		List<Category> listCategories = repo.findAll();
		model.addAttribute("listCategories",listCategories);
		return "categories";
	}
	
	@GetMapping("/categories/new")
		public String createCategory(Model model) {
			model.addAttribute("category", new Category());
			return "category_form";
	}
	
	@PostMapping("/categories/save")
		public String saveCategory(Category category) {
			repo.save(category);
			return "redirect:/categories";
	}
	
}
