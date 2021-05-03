package com.training.employeeservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.training.employeeservice.exception.EmployeeNotFoundException;
import com.training.employeeservice.model.Employee;
import com.training.employeeservice.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	
	@Autowired
	public EmployeeRepository employeeRepository;
	
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		
		// return ResponseEntity.ok(employees);
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}
	
	@GetMapping("/employees/{employeeId}")
	public ResponseEntity<Employee> getEmployeeDetails(@PathVariable int employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
		                                  .orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found."));
		
		// return ResponseEntity.ok(employee);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);		                             
	}
	
	@PostMapping("/employees")
	public ResponseEntity<Employee> createNewEmployee(@RequestBody Employee employee) {
		Employee savedEmployee = employeeRepository.save(employee);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("token", "sanknfkfwbfwbdmadadjewbjadas123223");
		headers.add("Cookie", "Secret");
		
		return new ResponseEntity<Employee>(savedEmployee, headers, HttpStatus.CREATED);		
	}
	
	@DeleteMapping("/employees/{employeeId}")
	public ResponseEntity<?> deleteEmployee(@PathVariable int employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
		                                                               .orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found"));
		
		employeeRepository.delete(employee);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/employees/{employeeId}")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
		Employee updatedEmployee = employeeRepository.save(employee);
		
		return new ResponseEntity<Employee>(updatedEmployee, HttpStatus.OK);
	}
}











