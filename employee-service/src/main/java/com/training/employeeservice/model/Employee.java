package com.training.employeeservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="employees")
@Data
@ApiModel(description="Employee entity")
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	
     @Id
     @GeneratedValue(strategy=GenerationType.AUTO)
     @Column(name="employee_id")
	 private int employeeId;
     @ApiModelProperty(notes="Employee name and it should not be null")
	 private String name;
	 private String department;
	 private double salary;
	 
}
