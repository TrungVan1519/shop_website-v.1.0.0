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

import com.dauXanh.dao.ProductSizeDAO;
import com.dauXanh.entity.ProductSize;

@Repository
@Transactional
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ProductSizeDAOImpl implements ProductSizeDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<ProductSize> getProductSizes() {
		Session session = sessionFactory.getCurrentSession();
		try {
			String sql = "FROM ProductSize";
			Query<ProductSize> query = session.createQuery(sql, ProductSize.class);

			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ProductSize getProductSizeById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(ProductSize.class, id);
	}
}
