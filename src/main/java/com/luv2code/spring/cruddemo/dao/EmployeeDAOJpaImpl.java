package com.luv2code.spring.cruddemo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.luv2code.spring.cruddemo.entity.Employee;

public class EmployeeDAOJpaImpl implements EmployeeDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Employee> findAll() {
		
		TypedQuery<Employee> query = entityManager.createQuery("from Employee", Employee.class);
		List<Employee> employees = query.getResultList();
		
		return employees;
	}

	@Override
	public List<Employee> findById(long id) {
		
		Employee employee = entityManager.find(Employee.class, id);
		
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(employee);
		
		return employees;
	}

	@Override
	public void save(Employee employee) {
		
		//if id==0 then it saves, if id!=0 then it does an update
		Employee empl = entityManager.merge(employee);
		
		//update with id from db... so we can get generated id for save/insert; Useful in our REST API to return generated id 
		empl.setId(employee.getId());
	}

	@Override
	public void delete(long id) {
		
		TypedQuery<Employee> query = entityManager.createQuery("delete from Employee where id:theId", Employee.class);
		query.setParameter("theId", id);
		
		query.executeUpdate();
	}

	
}
