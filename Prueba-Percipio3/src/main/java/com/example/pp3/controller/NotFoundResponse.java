package com.example.pp3.controller;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.pp3.exception.NotFoundException;

@ControllerAdvice
public class NotFoundResponse {

	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	protected String notFoundHandler(NotFoundException exception) {
		return exception.getMessage();
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoSuchElementException.class)
	protected String notSuchHandler(NoSuchElementException exception) {
		return "No se encontro el elemento buscado";
	}
}