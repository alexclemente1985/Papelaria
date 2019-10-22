package com.alexandre.papelaria.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class RepeatedBarcodeException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4134405141430072748L;
	
	public RepeatedBarcodeException(String errMsg) {
		super(errMsg);
	}
}
