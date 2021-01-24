package com.dauXanh.dao.impl;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dauXanh.dao.ProductDAO;
import com.dauXanh.entity.Product;
import com.dauXanh.entity.ProductDetail;

@Repository
@Transactional
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Product> getProducts(int pageNumber, int pageSize) {
		Session session = sessionFactory.getCurrentSession();
		try {
			String sql = "FROM Product";
			Query<Product> query = session.createQuery(sql, Product.class);
			query.setFirstResult((pageNumber - 1) * pageSize);
			query.setMaxResults(pageSize);

			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Product getProductById(int id) {
		Session session = sessionFactory.getCurrentSession();
		try {
			String sql = "FROM Product WHERE id = :id";
			Query<Product> query = session.createQuery(sql, Product.class);
			query.setParameter("id", id);

			return query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Product> getProductsByCategoryId(int categoryId) {
		Session session = sessionFactory.getCurrentSession();
		try {
			String sql = "FROM Product WHERE category.id = :id";
			Query<Product> query = session.createQuery(sql, Product.class);
			query.setParameter("id", categoryId);

			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Long countAllProducts() {
		Session session = sessionFactory.getCurrentSession();
		try {
			String sql = "SELECT COUNT(*) FROM Product";
			Query<Long> query = session.createQuery(sql, Long.class);

			return (Long) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return 0L;
		}
	}

	@Override
	public boolean removeProductById(int productId) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Product product = session.get(Product.class, productId);
			if (product != null) {

				Set<ProductDetail> productDetails = product.getProductDetails();
				if (productDetails != null && productDetails.size() > 0) {
					productDetails.forEach(productDetail -> {
						// Have to delete billDetail manually becauseof @EmbeddedId
						session.createQuery(
								"DELETE FROM BillDetail WHERE id.productDetailId = " + productDetail.getId())
								.executeUpdate();

						// Dont delete Bill by cascading delete via BillDetail because I want that
					});
				}

				// automatically delete productDetail and product becauseof Cascading.ALL in
				// Product Entity
				session.delete(product);

				return true;
			}

			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean addProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
		int id = (int) session.save(product);

		if (id > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
		Product updatedProduct = (Product) session.merge(product);

		if (updatedProduct != null) {
			return true;
		}

		return false;
	}
}
