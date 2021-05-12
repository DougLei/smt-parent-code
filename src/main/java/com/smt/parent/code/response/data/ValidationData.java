package com.smt.parent.code.response.data;

/**
 * 
 * @author DougLei
 */
public class ValidationData extends Data{
	private String field; 
	public String getField() {
		return field;
	}
	
	/**
	 * 
	 * @param data 验证的数据
	 * @param field 验证失败的属性名
	 * @param message 失败原因(String.format格式)
	 * @param code 失败原因编码
	 * @param params 原因值参数数组
	 */
	public ValidationData(Object data, String field, String message, String code, Object... params) {
		super(data, message, code, params);
		this.field = field;
	}
}
