package com.dauXanh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dauXanh.dao.CategoryDAO;
import com.dauXanh.entity.Category;
import com.dauXanh.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDAO categoryDAO;
	
	@Override
	public List<Category> getCategories() {
		return categoryDAO.getCategories();
	}

	@Override
	public Category getCategoryById(int categoryId) {
		return categoryDAO.getCategoryById(categoryId);
	}
}
