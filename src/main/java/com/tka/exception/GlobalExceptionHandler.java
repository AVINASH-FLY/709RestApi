package com.tka.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> methodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
		//List<String>list=new ArrayList<>();
		//List<FieldError> fieldErrors=ex.getFieldErrors();
		Map<String, String>errorMap=new HashMap<>();
		List<FieldError> fieldErrors = ex.getFieldErrors();// alt + shift + L
		 
		  for (FieldError fieldError : fieldErrors) {
		  errorMap.put(fieldError.getField(),fieldError.getDefaultMessage());
		  //list.add(fieldError.getDefaultMessage());
		}
		  errorMap.put("path",request.getRequestURI());// gives path where error occuring
		  return errorMap; // key and values giving eg.(productId:productNmae must be alphanumeric)
		//return list;     //only values(message for error will give) will give
	}
	
	@ExceptionHandler(ArithmeticException.class)
	public String arithmeticException(ArithmeticException ex) {
		return ex.getMessage();
	}
	@ExceptionHandler(ResourceAlreadyExistException.class)
	@ResponseStatus(code=HttpStatus.CONFLICT)
	public ExceptionResponse resourceAlreadyExistException(ResourceAlreadyExistException ex,HttpServletRequest request) {
		ExceptionResponse response = new ExceptionResponse();
		
		response.setMessage(ex.getMessage());
		response.setPath(request.getRequestURI());
		response.setStatuscode(HttpStatus.CONFLICT);
		response.setTime(LocalDateTime.now());
		
		return response;
	}
	
	@ExceptionHandler(SomethingWentWrongException.class)
	@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionResponse somethingWentWrongException(SomethingWentWrongException ex,HttpServletRequest request) {
		ExceptionResponse response = new ExceptionResponse();
		
		response.setMessage(ex.getMessage());
		response.setPath(request.getRequestURI());
		response.setStatuscode(HttpStatus.INTERNAL_SERVER_ERROR);
		response.setTime(LocalDateTime.now());
		
		return response;
	}
	
	@ExceptionHandler(ResourceNotExistException.class)
	@ResponseStatus(code=HttpStatus.OK)
	public ExceptionResponse resourceNotExistException (ResourceNotExistException ex,HttpServletRequest request) {
		ExceptionResponse response = new ExceptionResponse();
		
		response.setMessage(ex.getMessage());
		response.setPath(request.getRequestURI());
		response.setStatuscode(HttpStatus.NO_CONTENT);
		response.setTime(LocalDateTime.now());
		
		return response;
	}
}
