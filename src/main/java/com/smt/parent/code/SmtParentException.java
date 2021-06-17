package com.smt.parent.code;

/**
 * 
 * @author DougLei
 */
public class SmtParentException extends RuntimeException {

	public SmtParentException() {}
	public SmtParentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	public SmtParentException(String message, Throwable cause) {
		super(message, cause);
	}
	public SmtParentException(String message) {
		super(message);
	}
	public SmtParentException(Throwable cause) {
		super(cause);
	}
}
