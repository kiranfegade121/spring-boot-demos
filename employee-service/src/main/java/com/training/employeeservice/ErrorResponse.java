package com.training.employeeservice;

public class ErrorResponse {

	private String timestamp;
	private String errorMessage;
	private int errorCode;

	public ErrorResponse() {
		super();
	}

	public ErrorResponse(String timestamp, String errorMessage, int errorCode) {
		super();
		this.timestamp = timestamp;
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
