package com.inventory.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.inventory.demo.model.Brand;
import com.inventory.demo.model.Category;
import com.inventory.demo.repositories.BrandRepository;
import com.inventory.demo.repositories.CategoryRepository;

@Controller
public class BrandController {
	
	@Autowired
	private BrandRepository brandRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping("/brands/new")
	public String createBrand(Model model) {
		List<Category> listCategories = categoryRepository.findAll();
		
		model.addAttribute("listCategories", listCategories);
		model.addAttribute("brand", new Brand());
		return "brand_form";
	}
	
	@PostMapping("/brands/save")
	public String saveBrand(Brand brand) {
		brandRepository.save(brand);
		return "redirect:/brands";
	}
	
	@GetMapping("/brands")
	public String listBrands(Model model) {
		List<Brand> listBrands = brandRepository.findAll();
		model.addAttribute("listBrands",listBrands);
		return "brands";
	}
	
	@GetMapping("/brands/edit/{id}")
	public String editBrand(@PathVariable("id") Integer id, Model model) {
		List<Category> listCategories = categoryRepository.findAll();
		Brand brand = brandRepository.findById(id).get();
		model.addAttribute("listCategories", listCategories);
		model.addAttribute("brand", brand);
		
		return "brand_form";
	}
}
