package com.dauXanh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dauXanh.dao.EmployeeDAO;
import com.dauXanh.entity.Employee;
import com.dauXanh.service.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDAO employeeDAO;

	@Override
	public boolean validateEmployee(String email, String password) {
		return employeeDAO.validateEmployee(email, password);
	}

	@Override
	public List<Employee> getEmployees() {
		return employeeDAO.getEmployees();
	}

	@Override
	public boolean addEmployee(Employee employee) {
		return employeeDAO.addEmployee(employee);
	}
}
