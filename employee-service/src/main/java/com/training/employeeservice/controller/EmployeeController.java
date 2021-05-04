package com.training.employeeservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.employeeservice.exception.EmployeeNotFoundException;
import com.training.employeeservice.model.Employee;
import com.training.employeeservice.repository.EmployeeRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

//@CrossOrigin(origins="http://localhost:4200/")
@RestController
@RequestMapping("/api/v1")
@Api(description="CRUD operations in Employee Controller")
public class EmployeeController {
	
	@Autowired
	public EmployeeRepository employeeRepository;
	
	@GetMapping("/employees")
	@ApiOperation(value="Get All Employee Details", response=List.class)
	@ApiResponses(value = {
			@ApiResponse(code=200, message="Successfully retrieved Employee List"),
			@ApiResponse(code=404, message="The resource you were trying to reach is not found"),
			@ApiResponse(code=401, message="You are not authorized to access this resource."),
			@ApiResponse(code=403, message="Accessing the resource you were trying to reach is forbidden")
	})
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		
		// return ResponseEntity.ok(employees);
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}
	
	@GetMapping(value="/employees/{employeeId}")
	@ApiOperation(value="Get Employee details by Id", response=Employee.class)
	public ResponseEntity<Employee> getEmployeeDetails(@PathVariable int employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
		                                                               .orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found."));
		
		// return ResponseEntity.ok(employee);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);		                             
	}
	
	@PostMapping("/employees")
	@ApiOperation(value="Add New Employee", response=Employee.class)
	public ResponseEntity<Employee> createNewEmployee(@RequestBody Employee employee) {
		Employee savedEmployee = employeeRepository.save(employee);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("token", "sanknfkfwbfwbdmadadjewbjadas123223");
		headers.add("Cookie", "Secret");
		
		return new ResponseEntity<Employee>(savedEmployee, headers, HttpStatus.CREATED);		
	}
	
	@DeleteMapping("/employees/{employeeId}")
	@ApiOperation("Delete Employee by Id")
	public ResponseEntity<?> deleteEmployee(@PathVariable int employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
		                                                               .orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found"));
		
		employeeRepository.delete(employee);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/employees/{employeeId}")
	@ApiOperation(value="Update Employee details", response=Employee.class)
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
		Employee updatedEmployee = employeeRepository.save(employee);
		
		return new ResponseEntity<Employee>(updatedEmployee, HttpStatus.OK);
	}
	
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<Object> handleException(Exception ex) {
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("timestamp", LocalDateTime.now().toString());
//		headers.add("message", ex.getMessage());
//		headers.add("key", "value");
//		
//		return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
//	}
	
//	@ExceptionHandler(EmployeeNotFoundException.class)
//	public ResponseEntity<Object> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
//		
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("timestamp", LocalDateTime.now().toString());
//		headers.add("message", ex.getMessage());
//		
//		return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
//	}
}











