package com.example.pp3.exception;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1056364669752265413L;

	public NotFoundException(String id) {
		super("Not found elemente with id: " + id);
	}
}