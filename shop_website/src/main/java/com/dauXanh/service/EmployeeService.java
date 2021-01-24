package com.dauXanh.service;

import java.util.List;

import com.dauXanh.entity.Employee;

public interface EmployeeService {
	
	boolean validateEmployee(String email, String password);
	List<Employee> getEmployees();
	boolean addEmployee(Employee employee);
}
