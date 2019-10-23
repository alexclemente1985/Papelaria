package com.alexandre.papelaria.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class EmptyDatabase extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5481527279791095496L;

	public EmptyDatabase(String errMsg) {
		super(errMsg);
	}
}
