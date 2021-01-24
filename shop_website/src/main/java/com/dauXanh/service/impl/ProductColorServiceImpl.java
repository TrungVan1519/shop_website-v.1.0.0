package com.dauXanh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dauXanh.dao.ProductColorDAO;
import com.dauXanh.entity.ProductColor;
import com.dauXanh.service.ProductColorService;

@Repository
@Transactional
public class ProductColorServiceImpl implements ProductColorService {

	@Autowired
	ProductColorDAO productColorDAO;

	@Override
	public List<ProductColor> getProductColors() {
		return productColorDAO.getProductColors();
	}

	@Override
	public ProductColor getProductColorById(int id) {
		return productColorDAO.getProductColorById(id);
	}
}
