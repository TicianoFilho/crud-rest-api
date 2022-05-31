/*Here I'm using the Java Persistence API - JPA for REST API*/

package com.luv2code.spring.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.spring.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

	private EntityManager entityManager;
	
	@Autowired
	public EmployeeDAOJpaImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<Employee> findAll() {
		Query theQuery = entityManager.createQuery("from Employee");
		List<Employee> employees = theQuery.getResultList();
		
		System.out.println("Using JPA API Repo");
		
		return employees;
	}

	@Override
	public List<Employee> findById(long id) {
		
		
		return null;
	}

	@Override
	public void save(Employee employee) {
		
	
	}

	@Override
	public void delete(long id) {
		
	
	}

	
}
