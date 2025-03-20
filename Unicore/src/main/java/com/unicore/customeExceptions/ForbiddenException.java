package com.unicore.customeExceptions;

public class ForbiddenException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ForbiddenException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ForbiddenException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ForbiddenException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
