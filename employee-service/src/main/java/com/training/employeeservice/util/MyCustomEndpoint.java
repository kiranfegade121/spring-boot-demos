package com.training.employeeservice.util;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id="details")
public class MyCustomEndpoint {
	
	@ReadOperation
	public String getApplicationName() {
		return "App Name - Employee Service";
	}
	
}
