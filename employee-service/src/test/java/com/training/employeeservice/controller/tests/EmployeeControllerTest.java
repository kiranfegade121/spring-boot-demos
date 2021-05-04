package com.training.employeeservice.controller.tests;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.employeeservice.controller.EmployeeController;
import com.training.employeeservice.exception.EmployeeNotFoundException;
import com.training.employeeservice.model.Employee;
import com.training.employeeservice.repository.EmployeeRepository;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EmployeeRepository employeeRepository;
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void shouldReturnEmployeeWhenGetEmployeeDetailsIsSuccessfullyExecuted() throws Exception {
			
		Employee employee = new Employee(1, "Alex Browning", "HR", 34000);
		
		when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
		
		mockMvc.perform(get("/api/v1/employees/1"))
		             .andExpect(status().isOk())
		             .andExpect(jsonPath("$.employeeId", Matchers.equalTo(1)))
		             .andExpect(jsonPath("$.name", Matchers.equalTo("Alex Browning")))
		             .andExpect(jsonPath("$.department", Matchers.equalTo("HR")))
		             .andExpect(jsonPath("$.salary", Matchers.equalTo(34000.0)));
	}
	
	@Test
	public void shouldReturnNotFoundResponseWhenGetEmployeeDetailsThrowsAnException() throws Exception {
		
		EmployeeNotFoundException ex = new EmployeeNotFoundException("Employee Not Found");
		
		when(employeeRepository.findById(1)).thenThrow(ex);
		
		mockMvc.perform(get("/api/v1/employees/1"))
		             .andExpect(status().isNotFound())
		             .andExpect(jsonPath("$.errorMessage", Matchers.equalTo(ex.getMessage())))
		             .andExpect(jsonPath("$.errorCode", Matchers.equalTo(404)));		             
	}
	
	@Test
	public void getAllEmployeesShouldReturnListOfEmployees() throws Exception {
		
		List<Employee> employees = Arrays.asList(new Employee(1, "Alex Browninig", "HR", 34000.0), 
				                                                       new Employee(2, "Anna Parker", "ADMIN", 54000.0),
				                                                       new Employee(3, "Bob Martin", "SALES", 23000.0)); 
		
		when(employeeRepository.findAll()).thenReturn(employees);
		
		mockMvc.perform(get("/api/v1/employees"))
		             .andExpect(status().isOk())
		             .andExpect(jsonPath("$", Matchers.hasSize(3)))
		             .andExpect(jsonPath("$[1].name", Matchers.equalTo("Anna Parker")));
	}
	
	@Test
	public void createNewEmployeeShouldCreateNewEmployeeResource() throws JsonProcessingException, Exception {
		
		Employee employee = new Employee();
		employee.setEmployeeId(5);
		employee.setName("John Connor");
		employee.setDepartment("HR");
		employee.setSalary(34000.0);
		
		when(employeeRepository.save(Mockito.isA(Employee.class))).thenReturn(employee);
		
		Employee payload = new Employee();
		payload.setName("John Connor");
		payload.setDepartment("HR");
		payload.setSalary(34000.0);
		
		mockMvc.perform(post("/api/v1/employees").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(payload)).accept(MediaType.APPLICATION_JSON))
		             .andExpect(status().isCreated())
		             .andExpect(jsonPath("$.employeeId", Matchers.equalTo(5)))
		             .andExpect(jsonPath("$.name", Matchers.equalTo("John Connor")));
	}
	
	@Test
	public void deleteEmployeeShouldDeleteEmployee() throws Exception {
		
		 when(employeeRepository.findById(1)).thenReturn(Optional.of(new Employee(1, "Alex Browning", "HR", 34000)));
	     doNothing().when(employeeRepository).delete(Mockito.isA(Employee.class));
	     
	     mockMvc.perform(delete("/api/v1/employees/1"))
	                  .andExpect(status().isNoContent());
	}	
}











