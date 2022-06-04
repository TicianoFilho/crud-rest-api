/*Here we are using only for Spring Data JPA
 * Note: the @Transactional annotation is not needed here because JpaRepository provides this functionality*/

package com.luv2code.spring.cruddemo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.spring.cruddemo.dao.EmployeeRepository;
import com.luv2code.spring.cruddemo.entity.Employee;

@Service
public class EmployeeSpringDataJpaService implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> findAll() {
		System.out.println("using Spring Data JPA repository");
		return employeeRepository.findAll();
	}

	@Override
	public List<Employee> findById(long id) {
		Optional<Employee> result = employeeRepository.findById(id);
		
		List<Employee> theEmployee= new ArrayList<>();
		
		if (result.isPresent()) {
			theEmployee.add(result.get()); 
		} else {
			throw new RuntimeException("Employee not found with id: " + id);
		}
		return theEmployee;
	}

	@Override
	public void save(Employee employee) {
		employeeRepository.save(employee);
	}

	@Override
	public void delete(long id) {
		employeeRepository.deleteById(id);
	}

}
