package com.smt.parent.code.response;

/**
 * 
 * @author DougLei
 */
public enum State {
	
	/**
	 * 未知: -2
	 */
	UNKNOW(-2),
	/**
	 * 异常: -1
	 */
	EXCEPTION(-1),
	/**
	 * 失败: 0
	 */
	FAILURE(0),
	/**
	 * 成功: 1
	 */
	SUCCESS(1),
	/**
	 * 部分成功: 2
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
