package com.inventory.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.inventory.demo.model.Category;
import com.inventory.demo.model.Product;
import com.inventory.demo.repositories.CategoryRepository;
import com.inventory.demo.repositories.ProductRepository;

@Controller
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping("/products/new")
	public String createProduct(Model model) {
		List<Category> listCategories = categoryRepository.findAll();//Llamo la lista de categorias y la muestro en mi vista del formulario	de productos
		model.addAttribute("product", new Product());
		model.addAttribute("listCategories", listCategories);
		return "product_form";
	}
	
	@PostMapping("/products/save")
	public String saveProduct(Product product, HttpServletRequest request) {
		String[] detailIDs = request.getParameterValues("detailID");
		String[] detailNames = request.getParameterValues("detailName");
		String[] detailValues = request.getParameterValues("detailValue");
		
		for(int i = 0; i<detailNames.length; i++) 
			if(detailIDs !=null && detailIDs.length > 0) {
				product.setDetail(Integer.valueOf(detailIDs[i]), detailNames[i], detailValues[i]);
			}
			else{
			product.addDetail(detailNames[i], detailValues[i]);
		}
		
		productRepository.save(product);
		return "redirect:/products";
	}
	
	@GetMapping("/products")
	public String listProducts(Model model) {
		List<Product> listProducts = productRepository.findAll();
		model.addAttribute("listProducts", listProducts);
		return "products";
	}
	
	@GetMapping("/products/edit/{id}")
	public String editProduct(@PathVariable("id") Integer id, Model model) {
		Product product = productRepository.findById(id).get();
		model.addAttribute("product", product);
		List<Category> listCategories = categoryRepository.findAll();
		model.addAttribute("listCategories", listCategories);
		return "product_form";
	}
	@GetMapping("/products/delete/{id}")
	public String deleteProduct(@PathVariable("id") Integer id, Model model) {
		productRepository.deleteById(id);
		return"redirect:/products";
	}
}