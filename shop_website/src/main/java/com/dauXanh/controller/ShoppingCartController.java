package com.dauXanh.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dauXanh.dto.ShoppingCartItemDTO;
import com.dauXanh.entity.Bill;
import com.dauXanh.entity.BillDetail;
import com.dauXanh.entity.embbeded.BillDetailId;
import com.dauXanh.service.BillDetailService;
import com.dauXanh.service.BillService;
import com.dauXanh.service.CategoryService;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	BillService billService;

	@Autowired
	BillDetailService billDetailService;

	@GetMapping
	String getShoppingCarts(HttpServletRequest req, HttpSession session) {

		if (session.getAttribute("shopping-cart") != null) {
			List<ShoppingCartItemDTO> shoppingCart = (List<ShoppingCartItemDTO>) session.getAttribute("shopping-cart");
			req.setAttribute("shoppingCartSize", shoppingCart.size());
			req.setAttribute("shoppingCart", shoppingCart);
		}
		req.setAttribute("categories", categoryService.getCategories());

		return "shopping-cart";
	}

	@PostMapping
	String addBill(HttpServletRequest req, HttpSession session, @RequestParam String userName,
			@RequestParam String phoneNumber, @RequestParam String shipAddress) {

		if (session.getAttribute("shopping-cart") != null) {
			List<ShoppingCartItemDTO> shoppingCart = (List<ShoppingCartItemDTO>) session.getAttribute("shopping-cart");

			Bill bill = new Bill();
			bill.setUserName(userName);
			bill.setPhoneNumber(phoneNumber);
			bill.setShipAddress(shipAddress);

			int billId = billService.addBill(bill);

			if (billId > 0) {
				for (ShoppingCartItemDTO item : shoppingCart) {
					BillDetail billDetail = new BillDetail();
					billDetail.setCost(item.getCost() + "");
					billDetail.setId(new BillDetailId(billId, item.getProductDetailId()));
					billDetail.setQty(item.getQty());

					billDetailService.addBillDetail(billDetail);
				}
			}
		}
		req.setAttribute("categories", categoryService.getCategories());

		return "shopping-cart";
	}
}
