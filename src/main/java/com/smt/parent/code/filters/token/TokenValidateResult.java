package com.smt.parent.code.filters.token;

/**
 * 
 * @author DougLei
 */
public class TokenValidateResult {
	private boolean success; // 验证是否成功
	private TokenEntity entity; // token数据, 验证成功时会存储相应的数据
	private String message; // 记录验证失败时的消息
	private String code; // 记录验证失败时的国际化编码
	
	public TokenValidateResult() {}
	public TokenValidateResult(TokenEntity entity) {
		this.success = true;
		this.entity = entity;
	}
	public TokenValidateResult(TokenEntity entity, String message, String code) {
		this.entity = entity;
		this.message = message;
		this.code = code;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
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
}
