package com.training.employeeservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="employees")
@Data
public class Employee {
	
     @Id
     @Column(name="employee_id")
	 private int employeeId;
	 private String name;
	 private String department;
	 private double salary;
}
