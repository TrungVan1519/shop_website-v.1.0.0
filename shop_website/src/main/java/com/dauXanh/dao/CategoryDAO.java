package com.dauXanh.dao;

import java.util.List;

import com.dauXanh.entity.Category;

public interface CategoryDAO {
	
	List<Category> getCategories();
	Category getCategoryById(int categoryId);
}
