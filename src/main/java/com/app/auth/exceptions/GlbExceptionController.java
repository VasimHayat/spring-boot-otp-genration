package com.app.auth.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlbExceptionController {

	@ExceptionHandler(value= GlbException.class)
	public ResponseEntity<Object> exception(GlbException ex){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error", true);
		map.put("message", ex.getMessage());
		map.put("exception", "Handle by global exception hanler");
		
		return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
	}
	
}
