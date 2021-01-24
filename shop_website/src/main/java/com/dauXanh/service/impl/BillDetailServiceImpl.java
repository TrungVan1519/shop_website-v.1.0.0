package com.dauXanh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dauXanh.dao.BillDetailDAO;
import com.dauXanh.entity.BillDetail;
import com.dauXanh.service.BillDetailService;

@Service
@Transactional
public class BillDetailServiceImpl implements BillDetailService {
	
	@Autowired
	BillDetailDAO billDetailDAO;

	@Override
	public boolean addBillDetail(BillDetail billDetail) {
		return billDetailDAO.addBillDetail(billDetail);
	}
}
