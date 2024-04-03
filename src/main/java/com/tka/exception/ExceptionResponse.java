package com.tka.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class ExceptionResponse {

	private LocalDateTime time;
	private String path;
	private HttpStatus statuscode;
	private String message;
	
	public ExceptionResponse() {
		
	}

	public LocalDateTime getTime() {
		return time;
	}
    public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public String getPath() {
		return path;
	}
    public void setPath(String path) {
		this.path = path;
	}

	public HttpStatus getStatuscode() {
		return statuscode;
	}
    public void setStatuscode(HttpStatus statuscode) {
		this.statuscode = statuscode;
	}

	public String getMessage() {
		return message;
	}
    public void setMessage(String message) {
		this.message = message;
	}
	
}
