package com.airline.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@ResponseBody
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<String> handleException(ResourceNotFoundException excep, WebRequest req){
		return new ResponseEntity<String>(excep.getMessage(), HttpStatus.NOT_FOUND);
	}
}
