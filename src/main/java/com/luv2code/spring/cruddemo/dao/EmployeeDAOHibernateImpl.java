/*Here we are using the following DAO technique: Use EntityManager but leverage Hibernate API*/

package com.luv2code.spring.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.spring.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

	private EntityManager entityManager;
	
	//Set up constructor injection
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public List<Employee> findAll() {
		
		//Get the current Hibernate session
		Session session = entityManager.unwrap(Session.class);
		
		//Create a query
		Query<Employee> theQuery = session.createQuery("from Employee", Employee.class);
		
		//Execute query and get the result list
		List<Employee> employees = theQuery.getResultList();
		
		System.out.println("Using native Hibernate API");
		
		return employees;
	}

	@Override
	public List<Employee> findById(long id) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Employee> theQuery = session.createQuery("from Employee where id = :id", Employee.class);
		
		/*If the method result ware Employee and not a List<Employee>, the following code would work.
		Employee employee = session.get(Employee.class, id);*/	
		
		theQuery.setParameter("id", id);
		
		return theQuery.getResultList();
	}

	@Override
	public void save(Employee employee) {
		Session session = entityManager.unwrap(Session.class);
		
		//If id=0 then it will save, otherwise if the id is different than 0 it will do an update
		session.saveOrUpdate(employee);
		
	}

	@Override
	public void delete(long id) {
		Session session = entityManager.unwrap(Session.class);
		
		Query thequeQuery = session.createQuery("delete from Employee where id=:theId");
		thequeQuery.setParameter("theId", id);
		thequeQuery.executeUpdate();
	}

	
}
