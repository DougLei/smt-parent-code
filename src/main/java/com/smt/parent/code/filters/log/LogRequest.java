package com.smt.parent.code.filters.log;

import java.util.Date;

import com.smt.parent.code.response.Response;

/**
 * 
 * @author DougLei
 */
public class LogRequest {
	private int id;
	private int operationId;
	private String apiAddr;
	private String method;
	private String urlData;
	private String reqBody;
	private Date reqDate;
	private String respBody;
	private Date respDate;
	private int state;
	private int order;
	private String description;
	
	/**
	 * 设置响应信息
	 * @param response
	 * @param setBody 是否记录响应体
	 */
	public void setResponse(Response response, boolean setBody) {
		if(setBody)
			this.respBody = response.toJSONString();
		this.respDate = new Date();
		this.state = response.getState();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOperationId() {
		return operationId;
	}
	public void setOperationId(int operationId) {
		this.operationId = operationId;
	}
	public String getApiAddr() {
		return apiAddr;
	}
	public void setApiAddr(String apiAddr) {
		this.apiAddr = apiAddr;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getUrlData() {
		return urlData;
	}
	public void setUrlData(String urlData) {
		this.urlData = urlData;
	}
	public String getReqBody() {
		return reqBody;
	}
	public void setReqBody(String reqBody) {
		this.reqBody = reqBody;
	}
	public Date getReqDate() {
		return reqDate;
	}
	public void setReqDate(Date reqDate) {
		this.reqDate = reqDate;
	}
	public String getRespBody() {
		return respBody;
	}
	public void setRespBody(String respBody) {
		this.respBody = respBody;
	}
	public Date getRespDate() {
		return respDate;
	}
	public void setRespDate(Date respDate) {
		this.respDate = respDate;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
