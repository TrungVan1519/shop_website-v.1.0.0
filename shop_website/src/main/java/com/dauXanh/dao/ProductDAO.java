package com.dauXanh.dao;

import java.util.List;

import com.dauXanh.entity.Product;

public interface ProductDAO {
	List<Product> getProducts(int start, int offset);
	Product getProductById(int id);
	List<Product> getProductsByCategoryId(int categoryid);
	Long countAllProducts();
	boolean removeProductById(int productId);
	boolean addProduct(Product product);
	boolean updateProduct(Product product);
}
