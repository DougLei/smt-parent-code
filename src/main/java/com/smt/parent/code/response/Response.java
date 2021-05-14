package com.smt.parent.code.response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.smt.parent.code.response.data.Data;
import com.smt.parent.code.response.data.ErrorData;
import com.smt.parent.code.response.data.ExceptionData;
import com.smt.parent.code.response.data.ValidationData;

/**
 * 响应
 * @author DougLei
 */
public class Response {
	private boolean isBatch;// 是否是批量操作的响应
	private State state;
	private List<Object> data;
	private List<ValidationData> validation;
	private List<ErrorData> error;
	private ExceptionData exception;
	private String json; // response的json字符串
	
	public Response() {}
	public Response(Object data) {
		addData(data);
	}
	public Response(Object data, String field, String message, String code, Object... params) {
		addValidation(data, field, message, code, params);
	}
	public Response(Exception ex) {
		this.exception = new ExceptionData(ex);
	}
	
	/**
	 * 设置是否是批量响应
	 * @param isBatch
	 */
	public void setBatch(boolean isBatch) {
		this.isBatch = isBatch;
	}

	/**
	 * 添加正常处理的数据
	 * @param data 处理的数据
	 */
	public void addData(Object data) {
		if(this.data == null) 
			this.data = new ArrayList<Object>();
		this.data.add(data);
	}
	
	/**
	 * 添加验证失败的数据
	 * @param data 处理的数据
	 * @param field 验证失败的属性名
	 * @param message 失败原因(String.format格式)
	 * @param code 失败原因编码
	 * @param params 原因值参数数组
	 */
	public void addValidation(Object data, String field, String message, String code, Object... params) {
		if(this.validation == null) 
			this.validation = new ArrayList<ValidationData>();
		this.validation.add(new ValidationData(data, field, message, code, params));
	}
	
	/**
	 * 添加操作异常的数据
	 * @param data 处理的数据
	 * @param exception 操作异常
	 */
	public void addError(Object data, Exception exception) {
		if(this.error == null) 
			this.error = new ArrayList<ErrorData>();
		this.error.add(new ErrorData(data, exception));
	}
	
	// 修正state
	private void correctState() { 
		if(exception == null) {
			boolean validationEmpty = validation == null || validation.isEmpty(); // 验证的数据集合是否为空
			boolean errorEmpty = error == null || error.isEmpty(); // 操作异常的数据集合是否为空
			
			if(data == null || data.isEmpty()) {
				if(validationEmpty && errorEmpty) {
					this.state = State.SUCCESS;
				}else {
					this.state = State.FAILURE;
				}
			}else {
				if(validationEmpty && errorEmpty) {
					this.state = State.SUCCESS;
				}else {
					this.state = State.PARTIAL_SUCCESS;
				}
			}
		}else {
			this.state = State.EXCEPTION;
		}
	}
	// 获取数据
	private Object get(List<?> list) {
		if(isBatch) {
			if(list == null || list.isEmpty()) 
				return Collections.emptyList();
			return list;
		}
		if(list == null || list.isEmpty()) 
			return null;
		return list.get(0);
	}
	
	public int getState() {
		if(state == null) 
			correctState();
		return state.getValue();
	}
	public Object getData() {
		return get(data);
	}
	public Object getValidation() {
		return get(validation);
	}
	public Object getError() {
		return get(error);
	}
	public Data getException() {
		return exception;
	}
	/**
	 * 将Response转换为json字符串
	 * @return
	 */
	public String toJSONString() {
		if(json == null)
			json = JSONObject.toJSONString(this, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat);
		return json;
	}
}