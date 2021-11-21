package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ParrotException extends ResponseStatusException {
	private static final long serialVersionUID = 1L;

	public ParrotException(String mensaje) {
		super(HttpStatus.NOT_FOUND,mensaje);
	}
}
