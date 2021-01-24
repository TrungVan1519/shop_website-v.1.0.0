package com.dauXanh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dauXanh.dao.ProductDAO;
import com.dauXanh.entity.Product;
import com.dauXanh.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDAO productDAO;
	
	@Override
	public List<Product> getProducts(int start, int offset) {
		return productDAO.getProducts(start, offset);
	}

	@Override
	public Product getProductById(int id) {
		return productDAO.getProductById(id);
	}

	@Override
	public List<Product> getProductsByCategoryId(int categoryid) {
		return productDAO.getProductsByCategoryId(categoryid);
	}

	@Override
	public Long countAllProducts() {
		return productDAO.countAllProducts();
	}

	@Override
	public boolean removeProductById(int productId) {
		return productDAO.removeProductById(productId);
	}

	@Override
	public boolean addProduct(Product product) {
		return productDAO.addProduct(product);
	}

	@Override
	public boolean updateProduct(Product product) {
		return productDAO.updateProduct(product);
	}
}
