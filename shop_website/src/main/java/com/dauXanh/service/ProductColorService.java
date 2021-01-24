package com.dauXanh.service;

import java.util.List;

import com.dauXanh.entity.ProductColor;

public interface ProductColorService {
	List<ProductColor> getProductColors();
	ProductColor getProductColorById(int id);
}
