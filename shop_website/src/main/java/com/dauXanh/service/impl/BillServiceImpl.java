package com.dauXanh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dauXanh.dao.BillDAO;
import com.dauXanh.entity.Bill;
import com.dauXanh.service.BillService;

@Service
@Transactional
public class BillServiceImpl implements BillService {

	@Autowired
	BillDAO billDAO;
	
	@Override
	public int addBill(Bill bill) {
		return billDAO.addBill(bill);
	}
}
