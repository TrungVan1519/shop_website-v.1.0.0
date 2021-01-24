package com.dauXanh.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dauXanh.dao.CategoryDAO;
import com.dauXanh.entity.Category;

@Repository
@Transactional
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Category> getCategories() {
		Session session = sessionFactory.getCurrentSession();
		try {
			String sql = "FROM Category";
			Query<Category> query = session.createQuery(sql, Category.class);

			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Category getCategoryById(int categoryId) {
		Session session = sessionFactory.getCurrentSession();
		try {
			String sql = "FROM Category WHERE id = :id";
			Query<Category> query = session.createQuery(sql, Category.class);
			query.setParameter("id", categoryId);

			return query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
