package com.smt.parent.code.response;

/**
 * 
 * @author DougLei
 */
public enum State {
	/**
	 * 异常
	 */
	EXCEPTION(-1),
	/**
	 * 失败
	 */
	FAILURE(0),
	/**
	 * 成功
	 */
	SUCCESS(1),
	/**
	 * 部分成功
	 */
	PARTIAL_SUCCESS(2);
	
	private int value;
	private State(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
}
