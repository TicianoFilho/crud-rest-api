/*We're using here Spring Data JPA*/

package com.luv2code.spring.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.spring.cruddemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	//That's it! No need to write any code here.
}
