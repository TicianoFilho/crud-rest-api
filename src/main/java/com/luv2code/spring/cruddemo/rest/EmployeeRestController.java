package com.luv2code.spring.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.spring.cruddemo.entity.Employee;
import com.luv2code.spring.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	@Autowired
	private EmployeeService employeeService;
	
	//Get all employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeService.findAll(); 
	}
	
	//Get Employee by Id
	@GetMapping("/employees/{id}")
	public List<Employee> getEmployeeById(@PathVariable long id) {
		return employeeService.findById(id);
	}
	
	//Save employee
	@PostMapping("/employees")
	public void save(@RequestBody Employee employee) {
		
		//Also just in case they pass an id in JSON... set id to 0
		//This is to force a save of a new item... instead of update
		employee.setId(0);
		
		employeeService.save(employee);
	}
	
	//Update employee
	@PutMapping("/employees")
	public Employee update(@RequestBody Employee employee) {
		employeeService.save(employee);
		return employee;
	}
	
	//Delete employee
	@DeleteMapping("/employees/{id}")
	public String delete(@PathVariable long id) {
		
		List<Employee> employee = this.getEmployeeById(id);		
		if (employee.isEmpty()) {
			throw new RuntimeException(String.format("Employee id not found: %s", id));
		}
		
		employeeService.delete(id);
		
		return "Employee deleted with id: " + id;
	}
	
}
