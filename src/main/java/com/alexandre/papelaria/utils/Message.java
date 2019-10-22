package com.alexandre.papelaria.utils;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class Message {
	@Autowired
	private MessageSource messageSource;

	private static Locale locale = Locale.US;

	public String repeatedBarcode(Integer bcode, String pname) {
		Object[] args = new Object[] { bcode,pname };
		return messageSource.getMessage("repeatedBarcode", args, locale);
	}

	public String emptyDatabase() {
		return messageSource.getMessage("emptyDatabase", null, locale);
	}

	public String productNotFound(Integer bcode) {
		Object[] args = new Object[] { bcode };
		return messageSource.getMessage("productNotFound", args, locale);
	}
	
	public String categoryNotFound(Integer id) {
		Object[] args = new Object[] {id};
		return messageSource.getMessage("categoryNotFound",args, locale);
	}
	
	public String invalidCategory() {
		return messageSource.getMessage("invalidCategory",null, locale);
	}
	
	public String nullBarcode() {
		return messageSource.getMessage("nullBarcode", null, locale);
	}
		
}
