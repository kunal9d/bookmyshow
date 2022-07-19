package com.example.exception;

import org.springframework.stereotype.Component;

@Component
public class EmptyInputException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7166231384202378188L;
	private String errorCode;
	private String errorMessage;
	public EmptyInputException() {
		super();
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public EmptyInputException(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}



}
