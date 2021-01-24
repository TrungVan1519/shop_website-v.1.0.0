package com.dauXanh.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dauXanh.dao.BillDAO;
import com.dauXanh.entity.Bill;

@Repository
@Transactional
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BillDAOImpl implements BillDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public int addBill(Bill bill) {
		Session session = sessionFactory.getCurrentSession();
		int id = (int) session.save(bill);
		return id;
	}
	
}
