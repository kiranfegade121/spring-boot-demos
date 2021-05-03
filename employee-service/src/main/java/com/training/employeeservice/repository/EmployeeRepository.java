package com.training.employeeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.employeeservice.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
