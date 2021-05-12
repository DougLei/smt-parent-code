package com.smt.parent.code.filters.token;

/**
 * 
 * @author DougLei
 */
public class TokenValidateResult {
	private String message; // 记录验证失败时的消息
	private String code; // 记录验证失败时的国际化编码
	private Object[] params; // 记录验证失败时的国际化编码需要的参数
	public TokenValidateResult(String message, String code, Object... params) {
		this.message = message;
		this.code = code;
		this.params = params;
	}
	/**
	 * 获取验证失败时的消息
	 * @return
	 */
	public String getMessage() {
		if(params.length > 0)
			return String.format(message, params);
		return message;
	}
	/**
	 * 获取验证失败时的国际化编码
	 * @return
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 获取验证失败时的国际化编码参数
	 * @return
	 */
	public Object[] getParams() {
		return params;
	}
	
	// ------------------------------------------------------------------------------------------
	private TokenEntity entity; // token数据, 验证成功时会存储相应的数据
	public TokenValidateResult(TokenEntity entity) {
		this.success=true;
		this.entity =entity;
	}
	
	/**
	 * 获取token实体
	 * @return
	 */
	public TokenEntity getEntity() {
		return entity;
	}
	
	// ------------------------------------------------------------------------------------------
	private boolean success; // 验证是否成功
	public boolean isSuccess() {
		return success;
	}
	public boolean isFail() {
		return !success;
	}
}
