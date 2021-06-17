package com.smt.parent.code.filters.token;

/**
 * 
 * @author DougLei
 */
public class TokenValidateResult {
	private boolean success; // 验证是否成功
	private String token; // token值
	private TokenEntity entity; // token数据, 验证成功时会存储相应的数据
	private String message; // 记录验证失败时的消息
	private String code; // 记录验证失败时的国际化编码
	private Object[] params; // 记录验证失败时的国际化编码需要的参数
	
	public TokenValidateResult() {}
	public TokenValidateResult(TokenEntity entity) {
		this.success = true;
		this.entity = entity;
	}
	public TokenValidateResult(String token, String message, String code, Object... params) {
		this.token = token;
		this.message = message;
		this.code = code;
		this.params = params;
	}
	public TokenValidateResult(TokenEntity entity, String message, String code, Object... params) {
		this.entity = entity;
		this.message = message;
		this.code = code;
		this.params = params;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public TokenEntity getEntity() {
		return entity;
	}
	public void setEntity(TokenEntity entity) {
		this.entity = entity;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Object[] getParams() {
		return params;
	}
	public void setParams(Object[] params) {
		this.params = params;
	}
}
