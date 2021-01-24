package com.dauXanh.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dauXanh.dto.ShoppingCartItemDTO;
import com.dauXanh.entity.Category;
import com.dauXanh.entity.Product;
import com.dauXanh.service.CategoryService;
import com.dauXanh.service.ProductService;

@Controller
@RequestMapping("/details")
public class DetailsController {

	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;

	@GetMapping("/product/{productId}")
	String getProductDetails(HttpServletRequest req, HttpSession session, @PathVariable(required = false) int productId) {
		Product product = productService.getProductById(productId);
		List<Category> categories = categoryService.getCategories();
		
		req.setAttribute("product", product);
		req.setAttribute("categories", categories); 
		
		if (session.getAttribute("shopping-cart") != null) {
			List<ShoppingCartItemDTO> shoppingCart = (List<ShoppingCartItemDTO>) session.getAttribute("shopping-cart");
			req.setAttribute("shoppingCartSize", shoppingCart.size());
		}
		
		return "product-details";
	}
}
