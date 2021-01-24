package com.dauXanh.dao;

import java.util.List;

import com.dauXanh.entity.Employee;

public interface EmployeeDAO {

	boolean validateEmployee(String email, String password);
	List<Employee> getEmployees();
	boolean addEmployee(Employee employee);
}
