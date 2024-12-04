package com.cg.exception.handler;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.dto.ApiError;

@RestControllerAdvice//Act as a catch block for entire app
public class GlobalExceptionHandler {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handle(Exception e){
		ApiError error = new ApiError();
		error.setMsg(e.getMessage());
		error.setErrorCode(404);
		return new ResponseEntity<ApiError>(error,HttpStatusCode.valueOf(404));
	}
}
