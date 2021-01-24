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

import com.dauXanh.dao.ProductColorDAO;
import com.dauXanh.entity.ProductColor;

@Repository
@Transactional
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ProductColorDAOImpl implements ProductColorDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<ProductColor> getProductColors() {
		Session session = sessionFactory.getCurrentSession();
		try {
			String sql = "FROM ProductColor";
			Query<ProductColor> query = session.createQuery(sql, ProductColor.class);

			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ProductColor getProductColorById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(ProductColor.class, id);
	}
}
