package com.smt.parent.code.response.data;

import com.douglei.tools.i18n.Message;

/**
 * 响应数据
 * @author DougLei
 */
public abstract class Data {
	private Object data;
	private Message message;
	
	public Data(Object data, String message, String code, Object... params) {
		this.data = data;
		this.message = new Message(message, code, params);
	}
	
	public Object getData() {
		return data;
	}
	public String getCode() {
		return message.getCode();
	}
	public String getMessage() {
		return message.getMessage();
	}
	public Object[] getParams() {
		return message.getParams();
	}
}
