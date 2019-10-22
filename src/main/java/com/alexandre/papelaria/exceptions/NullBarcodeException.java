package com.alexandre.papelaria.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class NullBarcodeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5199004437477899221L;

	public NullBarcodeException(String errMsg) {
		super(errMsg);
	}
}
