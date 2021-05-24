package com.smt.parent.code.spring.eureka.cloud.feign;

/**
 * 
 * @author DougLei
 */
public class APIException extends RuntimeException {

	public APIException(String message) {
		super(message);
	}
	public APIException(String message, Throwable cause) {
		super(message, cause);
	}
}
