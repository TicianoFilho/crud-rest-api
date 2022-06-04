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
		
		//if the result type was not a list but a single employee: 
		//Employee emp = entityManager.find(Employee.class, id);
		
		Query query = entityManager.createQuery("from Employee where id=:id");
		query.setParameter("id", id);
		List<Employee> employee = query.getResultList();
		
		return employee;
	}

	@Override
	public void save(Employee employee) {
		
		//If id==0 then it'll save otherwise it'll update.
		Employee dbEmployee = entityManager.merge(employee);
		
		//update with id from db... so we can get generated id from insert/save
		employee.setId(dbEmployee.getId());
	 
	}

	@Override
	public void delete(long id) {
		
		Query query = entityManager.createQuery("delete from Employee where id=:id");
		query.setParameter("id", id);
		
		query.executeUpdate();
	}

	
}
