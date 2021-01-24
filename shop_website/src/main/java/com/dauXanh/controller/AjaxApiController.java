package com.dauXanh.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dauXanh.dto.ProductDTO;
import com.dauXanh.dto.ShoppingCartItemDTO;
import com.dauXanh.entity.Category;
import com.dauXanh.entity.Product;
import com.dauXanh.entity.ProductColor;
import com.dauXanh.entity.ProductDetail;
import com.dauXanh.entity.ProductSize;
import com.dauXanh.service.CategoryService;
import com.dauXanh.service.EmployeeService;
import com.dauXanh.service.ProductColorService;
import com.dauXanh.service.ProductService;
import com.dauXanh.service.ProductSizeService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@ResponseBody
@RequestMapping("/ajax-api")
public class AjaxApiController {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	ProductService productService;

	@Autowired
	ProductSizeService productSizeService;

	@Autowired
	ProductColorService productColorService;

	@Autowired
	CategoryService categoryService;

	@PostMapping("/login")
	String login(@RequestParam String email, @RequestParam String password, HttpSession session) {

		boolean isValid = employeeService.validateEmployee(email, password);
		if (isValid) {
			// save value to session
			session.setAttribute("sessionEmail", email);
			session.setMaxInactiveInterval(60 * 3);
			return "/";
		}

		return "/auth/login";
	}

	@GetMapping("/add-shopping-cart")
	String addToShoppingCart(@RequestParam int productDetailId, @RequestParam int productId, @RequestParam int sizeId,
			@RequestParam int colorId, @RequestParam int qty, @RequestParam int cost, @RequestParam String productName,
			@RequestParam String size, @RequestParam String color, HttpSession session) {

		if (session.getAttribute("shopping-cart") == null) {
			session.setAttribute("shopping-cart", new ArrayList<ShoppingCartItemDTO>());
			session.setMaxInactiveInterval(60 * 3);
		}

		List<ShoppingCartItemDTO> shoppingCart = (List<ShoppingCartItemDTO>) session.getAttribute("shopping-cart");
		boolean isExist = false;
		for (ShoppingCartItemDTO item : shoppingCart) {
			if (item.getProductId() == productId && item.getSizeId() == sizeId && item.getColorId() == colorId) {
				item.setQty(item.getQty() + 1);
				isExist = true;
			}
		}
		if (!isExist) {
			shoppingCart.add(new ShoppingCartItemDTO(productDetailId, productId, sizeId, colorId, qty = 1, cost,
					productName, size, color));
		}

		return shoppingCart.size() + "";
	}

	@GetMapping("/update-shopping-cart")
	void updateShoppingCart(HttpSession session, @RequestParam int qty, @RequestParam int productId,
			@RequestParam int sizeId, @RequestParam int colorId) {
		if (session.getAttribute("shopping-cart") != null) {
			List<ShoppingCartItemDTO> shoppingCart = (List<ShoppingCartItemDTO>) session.getAttribute("shopping-cart");
			for (ShoppingCartItemDTO item : shoppingCart) {
				if (item.getProductId() == productId && item.getSizeId() == sizeId && item.getColorId() == colorId) {
					item.setQty(qty);
				}
			}
		}
	}

	@GetMapping("/remove-shopping-cart")
	void removeShoppingCart(HttpSession session, @RequestParam int productId, @RequestParam int sizeId,
			@RequestParam int colorId) {
		int pos = -1;
		if (session.getAttribute("shopping-cart") != null) {
			List<ShoppingCartItemDTO> shoppingCart = (List<ShoppingCartItemDTO>) session.getAttribute("shopping-cart");
			for (ShoppingCartItemDTO item : shoppingCart) {
				if (item.getProductId() == productId && item.getSizeId() == sizeId && item.getColorId() == colorId) {
					pos = shoppingCart.indexOf(item);
				}
			}
			if (pos > -1) {
				shoppingCart.remove(pos);
			}
		}
	}

	@GetMapping(path = "/paging", produces = "text/html; charset=UTF-8")
	String paging(@RequestParam int pageNumber) {
		int pageSize = 4;
		List<Product> products = productService.getProducts(pageNumber, pageSize);

		String html = "";
		for (Product product : products) {
			html += "<tr>";
			html += "<td><div class='custom-control custom-checkbox remove-product'><input type='checkbox' class='custom-control-input' value='"
					+ product.getId() + "'></div></td>";
			html += "<th scope='row' data-product-id='" + product.getId() + "'>" + product.getId() + "</th>";
			html += "<td>" + product.getName() + "</td>";
			html += "<td>" + product.getCost() + "$</td>";
			html += "<td><button type='button' class='btn btn-warning btn-update-product'>Update</button></td>";
			html += "</tr>";
		}
		return html;
	}

	@GetMapping("/remove-product")
	void removeProduct(@RequestParam int productId) {
		productService.removeProductById(productId);
	}

	@PostMapping("/upload-product-img")
	void uploadProductImg(MultipartHttpServletRequest mulReg) {
		Iterator<String> fileNames = mulReg.getFileNames();
		MultipartFile file = mulReg.getFile(fileNames.next());
		try {
			if (file != null) {
				// Cach 1:
				file.transferTo(new File(
						"E:/4 New Phase/2 Coding/3 Java/1 Java Web/3 Spring MVC Project/online-shop/online-shop/src/main/webapp/public/img/product-upload/"
								+ file.getOriginalFilename()));
				// Cach 2:
//				FileOutputStream outputStream = new FileOutputStream(new File(
//						"E:/4 New Phase/2 Coding/3 Java/1 Java Web/3 Spring MVC Project/online-shop/online-shop/src/main/webapp/public/img/product-upload/"
//								+ file.getOriginalFilename()));
//				outputStream.write(file.getBytes());
//				outputStream.close();
			} else {
				System.out.println("File not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PostMapping("/add-product")
	void addProduct(@RequestParam String json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode data = mapper.readTree(json);

			Product product = new Product();
			product.setName(data.get("productName").asText());
			product.setCost(data.get("productCost").asText());
			product.setDescription(data.get("productDescription").asText());
			product.setImg(data.get("productImg").asText());

			Category category = categoryService.getCategoryById(data.get("categoryId").asInt());
			if (category != null) {
				product.setCategory(category);
			}

			if (product.getProductDetails() == null) {
				product.setProductDetails(new HashSet<ProductDetail>());
			}
			JsonNode jsonProductDetails = data.get("productDetails");
			for (JsonNode jsonProductDetail : jsonProductDetails) {
				ProductDetail productDetail = new ProductDetail();
				productDetail.setQty(jsonProductDetail.get("productQty").asInt());

				ProductSize productSize = productSizeService
						.getProductSizeById(jsonProductDetail.get("productSizeId").asInt());
				if (productSize != null) {
					productDetail.setProductSize(productSize);
				}

				ProductColor productColor = productColorService
						.getProductColorById(jsonProductDetail.get("productColorId").asInt());
				if (productColor != null) {
					productDetail.setProductColor(productColor);
				}

				productDetail.setProduct(product); // > Have to do this
				product.getProductDetails().add(productDetail);
			}

			productService.addProduct(product);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@PostMapping(path = "/get-products-by-id", produces = "application/json; charset=UTF-8")
	ProductDTO getProductsById(@RequestParam int productId) {

		Product product = productService.getProductById(productId);

		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setCost(product.getCost());
		productDTO.setDescription(product.getDescription());
		productDTO.setImg(product.getImg());

		Category category = new Category();
		category.setId(product.getCategory().getId());
		category.setName(product.getCategory().getName());
		productDTO.setCategory(category);

		productDTO.setProductDetails(new HashSet<ProductDetail>());
		for (ProductDetail productDetail : product.getProductDetails()) {
			ProductDetail tmp = new ProductDetail();

			tmp.setId(productDetail.getId());
			tmp.setQty(productDetail.getQty());

			ProductSize productSize = new ProductSize();
			productSize.setId(productDetail.getProductSize().getId());
			productSize.setSize(productDetail.getProductSize().getSize());
			tmp.setProductSize(productSize);

			ProductColor productColor = new ProductColor();
			productColor.setId(productDetail.getProductColor().getId());
			productColor.setColor(productDetail.getProductColor().getColor());
			tmp.setProductColor(productColor);

			productDTO.getProductDetails().add(tmp);
		}

		productDTO.setSales(null);

		return productDTO;
	}

	@PostMapping("/update-product")
	void updateProduct(@RequestParam String json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode data = mapper.readTree(json);
			
			// Để update 1 Product ta làm theo 1 trong 2 cách sau:
			// Cach 1: new Product và setId theo id truyền vào là xong (nhanh nhưng không thích kiểu này lắm)
//			Product product = new Product();
//			product.setId(data.get("productId").asInt());
			
			// Cach 2: Dùng Service lấy Product theo id truyền vào (lâu hơn nhưng khi đọc lại dễ hiểu hơn cách 1)
			Product product = productService.getProductById(data.get("productId").asInt());
			product.setName(data.get("productName").asText());
			product.setCost(data.get("productCost").asText());
			product.setDescription(data.get("productDescription").asText());
			product.setImg(data.get("productImg").asText());

			Category category = categoryService.getCategoryById(data.get("categoryId").asInt());
			if (category != null) {
				product.setCategory(category);
			}

			product.setProductDetails(new HashSet<ProductDetail>());
			JsonNode jsonProductDetails = data.get("productDetails");
			for (JsonNode jsonProductDetail : jsonProductDetails) {
				
				// Vì ở đây khi update Product ta cũng update ProductDetail luôn (do Cascade.ALL), nên
				// cách update ProductDetail cũng tương tự như cách update Product (Sử dụng 1 trong 2 cách)
				// nhưng do ta chưa làm Service lấy ProductDetail theo id truyền vào nên ta dùng tạm cách 1
				ProductDetail productDetail = new ProductDetail();
				productDetail.setId(jsonProductDetail.get("productDetailId").asInt());
				productDetail.setQty(jsonProductDetail.get("productQty").asInt());

				ProductSize productSize = productSizeService
						.getProductSizeById(jsonProductDetail.get("productSizeId").asInt());
				if (productSize != null) {
					productDetail.setProductSize(productSize);
				}

				ProductColor productColor = productColorService
						.getProductColorById(jsonProductDetail.get("productColorId").asInt());
				if (productColor != null) {
					productDetail.setProductColor(productColor);
				}

				productDetail.setProduct(product); // > Have to do this
				product.getProductDetails().add(productDetail);
			}

			productService.updateProduct(product);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
