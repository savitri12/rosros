package com.ros.accounting.cashup.exceptions;

public class RestaurantNotFoundException extends Exception {

	public RestaurantNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RestaurantNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public RestaurantNotFoundException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public RestaurantNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public RestaurantNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
