package com.dauXanh.service;

import java.util.List;

import com.dauXanh.entity.Category;

public interface CategoryService {
	
	List<Category> getCategories();
	Category getCategoryById(int categoryId);
}
