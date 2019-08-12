package com.conf.bookingsystem.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * CLass to handle the exceptions thrown
 * 
 * @author SonaSach
 *
 */
@ControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(value= {ResourceServiceExceptions.class})
	public ResponseEntity<Object> handleUserServiceException(ResourceServiceExceptions ex, WebRequest request){
		

		CustomErrorJSONMessage customErrorJSONMessage = new CustomErrorJSONMessage(new Date(), ex.getMessage());
		
		return new ResponseEntity<>(customErrorJSONMessage, HttpHeaders.EMPTY, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value= {Exception.class})
	public ResponseEntity<Object> handleOthersException(Exception ex, WebRequest request){

		CustomErrorJSONMessage customErrorJSONMessage = new CustomErrorJSONMessage(new Date(), ex.getMessage());
		
		return new ResponseEntity<>(customErrorJSONMessage, HttpHeaders.EMPTY, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
