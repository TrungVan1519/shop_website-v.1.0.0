package com.dauXanh.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dauXanh.service.CategoryService;
import com.dauXanh.service.ProductColorService;
import com.dauXanh.service.ProductService;
import com.dauXanh.service.ProductSizeService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductSizeService productSizeService;
	
	@Autowired
	ProductColorService productColorService;

	@GetMapping
	String showAdmin() {
		return "dashboard-main";
	}
	
	@GetMapping("/product")
	String addProduct(HttpServletRequest req) {
		
		int pageSize = 4;
		
		req.setAttribute("products", productService.getProducts(1, pageSize));
		req.setAttribute("numberOfPages", Math.ceil((double) productService.countAllProducts() / pageSize));
		req.setAttribute("categories", categoryService.getCategories());
		req.setAttribute("productSizes", productSizeService.getProductSizes());
		req.setAttribute("productColors", productColorService.getProductColors());
		
		return "dashboard-product";
	}
}
