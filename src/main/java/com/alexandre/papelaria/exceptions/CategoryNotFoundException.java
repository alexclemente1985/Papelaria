package com.alexandre.papelaria.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoryNotFoundException extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7169829530939254883L;

	public CategoryNotFoundException(String errMsg) {
		super(errMsg);
	}
}
