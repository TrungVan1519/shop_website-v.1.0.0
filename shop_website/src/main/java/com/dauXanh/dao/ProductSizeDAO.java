package com.dauXanh.dao;

import java.util.List;

import com.dauXanh.entity.ProductSize;

public interface ProductSizeDAO {
	List<ProductSize> getProductSizes();
	ProductSize getProductSizeById(int id);
}
