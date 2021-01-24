package com.dauXanh.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dauXanh.dao.BillDetailDAO;
import com.dauXanh.entity.BillDetail;
import com.dauXanh.entity.embbeded.BillDetailId;

@Repository
@Transactional
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BillDetailDAOImpl implements BillDetailDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public boolean addBillDetail(BillDetail billDetail) {
		Session session = sessionFactory.getCurrentSession();
		BillDetailId id = (BillDetailId) session.save(billDetail);
		
		if (id != null) {
			return true;
		}
		
		return false;
	}
}
