package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice

public class ValidationHandler  extends ResponseEntityExceptionHandler {
	Logger logger= org.slf4j.LoggerFactory.getLogger(ValidationHandler.class);
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String meassage = error.getDefaultMessage();
			errors.put(fieldName, meassage);
		});
		logger.warn("-----------------------------Validation Error---------------------------------------");
		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
	}

	
	}
	

	

