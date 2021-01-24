package com.dauXanh.service;

import java.util.List;

import com.dauXanh.entity.ProductSize;

public interface ProductSizeService {
	List<ProductSize> getProductSizes();
	ProductSize getProductSizeById(int id);
}
