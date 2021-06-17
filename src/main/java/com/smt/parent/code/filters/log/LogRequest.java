package com.smt.parent.code.filters.log;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.smt.parent.code.response.Response;

/**
 * 
 * @author DougLei
 */
public class LogRequest {
	private int id;
	private int operationId;
	private String url;
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
	
	/**
	 * 转换为Map
	 * @return
	 */
	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>(16);
		map.put("OPERATION_ID", operationId);
		map.put("URL", url);
		map.put("METHOD", method);
		map.put("URL_DATA", urlData);
		map.put("REQ_BODY", reqBody);
		map.put("REQ_DATE", reqDate);
		map.put("RESP_BODY", respBody);
		map.put("RESP_DATE", respDate);
		map.put("STATE", state);
		map.put("ORDER_", order);
		map.put("DESCRIPTION", description);
		return map;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
