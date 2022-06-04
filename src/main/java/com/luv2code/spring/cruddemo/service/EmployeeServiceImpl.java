package com.luv2code.spring.cruddemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.spring.cruddemo.dao.EmployeeDAO;
import com.luv2code.spring.cruddemo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeDAO employeeDAO;
	
	//Using constructor Dependency injection (the @Qualifier may be employeeDAOJpaImpl or EmployeeDAOHibernateImpl)
	@Autowired
	public EmployeeServiceImpl(@Qualifier("employeeDAOJpaImpl") EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
		
	}
	
	@Override
	@Transactional
	public List<Employee> findAll() { 
		System.out.println("Not using Spring Data JPA");
		return employeeDAO.findAll();
	}

	@Override
	@Transactional
	public List<Employee> findById(long id) {
		return employeeDAO.findById(id);
	}

	@Override
	@Transactional
	public void save(Employee employee) {
		employeeDAO.save(employee);
	}

	@Override
	@Transactional
	public void delete(long id) {
		employeeDAO.delete(id);
	}

}
