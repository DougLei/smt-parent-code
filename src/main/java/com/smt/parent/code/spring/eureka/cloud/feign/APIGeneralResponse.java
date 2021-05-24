package com.smt.parent.code.spring.eureka.cloud.feign;

import com.smt.parent.code.response.State;

/**
 * 
 * @author DougLei
 */
public class APIGeneralResponse {
	private State state;
	private Object data;
	
	public State getState() {
		return state;
	}
	public void setState(int state) {
		this.state = State.valueOf(state);
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
