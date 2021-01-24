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

import com.dauXanh.dao.EmployeeDAO;
import com.dauXanh.entity.Employee;

@Repository
@Transactional
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public boolean validateEmployee(String email, String password) {
		Session session = sessionFactory.getCurrentSession();

		try {
			String sql = "FROM Employee WHERE email = :email AND password = :password";
			Query<Employee> query = session.createQuery(sql, Employee.class);
			query.setParameter("email", email);
			query.setParameter("password", password);
			
			List<Employee> employees = query.getResultList();
			if (employees.size() == 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Employee> getEmployees() {
		Session session = sessionFactory.getCurrentSession();

		String sql = "FROM Employee";
		Query<Employee> query = session.createQuery(sql, Employee.class);

		return query.getResultList();
	}

	@Override
	public boolean addEmployee(Employee employee) {
		Session session = sessionFactory.getCurrentSession();
		Integer employeeId = (Integer) session.save(employee);
		
		if (employeeId != null) {
			return true;
		}
		return false;
	}
}
