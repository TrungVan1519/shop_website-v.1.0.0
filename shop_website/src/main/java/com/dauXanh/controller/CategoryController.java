package com.dauXanh.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dauXanh.service.CategoryService;
import com.dauXanh.service.ProductService;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;

	@GetMapping("/{categoryId}")
	String showAll(HttpServletRequest req, @PathVariable int categoryId) {
		req.setAttribute("categories", categoryService.getCategories()); 
		req.setAttribute("products", productService.getProductsByCategoryId(categoryId));
		req.setAttribute("chosenCategory", categoryService.getCategoryById(categoryId));
		return "category";
	}
}
