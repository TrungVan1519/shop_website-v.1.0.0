package com.dauXanh.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.dauXanh.dto.ShoppingCartItemDTO;
import com.dauXanh.service.CategoryService;
import com.dauXanh.service.ProductService;

@Controller
public class HomeController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/")
	String showHome(HttpServletRequest req, HttpSession session) {
		if (session.getAttribute("sessionEmail") != null) {
			String sessionEmail = session.getAttribute("sessionEmail").toString();
			req.setAttribute("sessionEmail", sessionEmail);
		}  
		
		if (session.getAttribute("shopping-cart") != null) {
			List<ShoppingCartItemDTO> shoppingCart = (List<ShoppingCartItemDTO>) session.getAttribute("shopping-cart");
			req.setAttribute("shoppingCartSize", shoppingCart.size());
		}
		
		req.setAttribute("products", productService.getProducts(1, 8));
		req.setAttribute("categories", categoryService.getCategories()); 
		return "home";  
	} 
}
