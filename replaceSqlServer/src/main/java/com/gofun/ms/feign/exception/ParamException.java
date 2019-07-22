package com.gofun.ms.feign.exception;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ParamException {
	public Logger logger = LoggerFactory.getLogger(this.getClass());
	@ExceptionHandler({MissingServletRequestParameterException.class })
	@ResponseBody
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public String businessExceptionHandler(HttpServletResponse response, MissingServletRequestParameterException e) {
		logger.error(e.getMessage());
		return e.getMessage();
	}
	@ExceptionHandler({BindException.class })
	@ResponseBody
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public String businessExceptionHandler(HttpServletResponse response, BindException e) {
		response.setCharacterEncoding("utf-8");
		FieldError error = e.getFieldError();
		String field = error.getField();
		String code = error.getDefaultMessage();
		String message = String.format("%s", code);
		return message;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public String handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		BindingResult result = e.getBindingResult();
		FieldError error = result.getFieldError();
		String field = error.getField();
		String code = error.getDefaultMessage();
		String message = String.format("%s", code);
		return message;
	}
	
}
