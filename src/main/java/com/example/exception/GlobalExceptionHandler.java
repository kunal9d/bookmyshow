package com.example.exception;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice

	public class GlobalExceptionHandler {
	Logger logger= org.slf4j.LoggerFactory.getLogger(GlobalExceptionHandler.class);

		@ExceptionHandler(EmptyInputException.class)		//user generated
		public ResponseEntity<String> handleEmptyInputException(EmptyInputException emptyInputException){
			logger.error("EmptyInputException : "+ emptyInputException.getMessage() + " " + emptyInputException.getErrorCode());
			return new ResponseEntity<String>("Exception Occured "+emptyInputException.getErrorCode()+" : "+emptyInputException.getErrorMessage(),HttpStatus.BAD_REQUEST);
		}
		
		@ExceptionHandler(NoSuchElementException.class)								//predefined
		public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException elementException){
			logger.error("-----------------------Request can't be completed---------------------");
			return new ResponseEntity<String>("Request can't be completed",HttpStatus.NOT_FOUND);
		}
		
    	@ExceptionHandler(NullPointerException.class)								//predefined
		public ResponseEntity<String> handleNullPointerException(NullPointerException e){
    		logger.error("------------------------------Requested Property not availaible---------------------------------");
			return new ResponseEntity<String>("Requested Property not availaible",HttpStatus.BAD_REQUEST);
		}
    	
	}
