package com.dauXanh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dauXanh.dao.ProductSizeDAO;
import com.dauXanh.entity.ProductSize;
import com.dauXanh.service.ProductSizeService;

@Repository
@Transactional
public class ProductSizeServiceImpl implements ProductSizeService {

	@Autowired
	ProductSizeDAO productSizeDAO;

	@Override
	public List<ProductSize> getProductSizes() {
		return productSizeDAO.getProductSizes();
	}

	@Override
	public ProductSize getProductSizeById(int id) {
		return productSizeDAO.getProductSizeById(id);
	}
}
