package com.training.employeeservice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.training.employeeservice.exception.EmployeeNotFoundException;

@ControllerAdvice
public class MyGlobalExceptionHandlerClass extends ResponseEntityExceptionHandler  {
	
//	@ExceptionHandler(EmployeeNotFoundException.class)
//	public ResponseEntity<Object> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
//		
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("timestamp", LocalDateTime.now().toString());
//		headers.add("message", ex.getMessage());
//		
//		return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
//	}
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
		
		ErrorResponse response = new ErrorResponse(LocalDateTime.now().toString(), ex.getMessage(), 404);
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.NOT_FOUND);
	}
	
   @Override
   	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
   			HttpHeaders headers, HttpStatus status, WebRequest request) {
	   
	   headers.add("timestamp", LocalDateTime.now().toString());
	   headers.add("status", Integer.toString(status.value()));
	   
	   List<String> errors = ex.getBindingResult()
	                                      .getFieldErrors()
	                                      .stream()
	                                      .map(x -> x.getDefaultMessage())
	                                      .collect(Collectors.toList());
	   
	   headers.add("errors", errors.toString());
	   
	   return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
   }	
   
   @ExceptionHandler(MethodArgumentTypeMismatchException.class)
   public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
	 
	    HttpHeaders headers = new HttpHeaders();
		headers.add("timestamp", LocalDateTime.now().toString());
		
		String name = ex.getName();    // name of parameter
	    String type = ex.getRequiredType().getSimpleName();  // get expected type of parameter
	    Object value = ex.getValue();  //  get the actual arguments
	    String message = String.format("'%s' should be a valid '%s' and '%s' isn't", 
	                                   name, type, value);
	    System.out.println(message);
	    
	    headers.add("errorMessage", message);
	    return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
   }

}
