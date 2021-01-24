package com.dauXanh.dao;

import java.util.List;

import com.dauXanh.entity.ProductColor;

public interface ProductColorDAO {
	List<ProductColor> getProductColors();
	ProductColor getProductColorById(int id);
}
