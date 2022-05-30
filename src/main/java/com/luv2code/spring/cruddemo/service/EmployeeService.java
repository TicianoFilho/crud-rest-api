package com.luv2code.spring.cruddemo.service;

import java.util.List;

import com.luv2code.spring.cruddemo.entity.Employee;

public interface EmployeeService {

	public List<Employee> findAll();
	public List<Employee> findById(long id);
	public void save(Employee employee);
	public void delete(long id);
}
