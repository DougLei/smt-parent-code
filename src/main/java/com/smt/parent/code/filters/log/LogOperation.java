package com.smt.parent.code.filters.log;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author DougLei
 */
public class LogOperation {
	private int id;
	private String batch;
	private Integer type;
	private Integer level;
	private String description;
	private String moduleId;
	private String moduleName;
	private String componentId;
	private String componentName;
	private String funcId;
	private String funcName;
	private String clientIp;
	private String userId;
	private Date operDate;
	private String projectCode;
	private String tenantId;
	private String invalidToken; // 无效的token值
	private LogRequest logRequest;
	
	/**
	 * 转换为Map
	 * @return
	 */
	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>(32);
		map.put("BATCH", batch);
		map.put("TYPE_", type);
		map.put("LEVEL_", level);
		map.put("DESCRIPTION", description);
		map.put("MODULE_ID", moduleId);
		map.put("MODULE_NAME", moduleName);
		map.put("COMPONENT_ID", componentId);
		map.put("COMPONENT_NAME", componentName);
		map.put("FUNC_ID", funcId);
		map.put("FUNC_NAME", funcName);
		map.put("CLIENT_IP", clientIp);
		map.put("USER_ID", userId);
		map.put("OPER_DATE", operDate);
		return map;
	}
	
	/**
	 * 转换为无效Token的Map
	 * @return
	 */
	public Map<String, Object> toInvalidTokenMap() {
		Map<String, Object> map = new HashMap<String, Object>(16);
		map.put("TOKEN", invalidToken);
		map.put("CLIENT_IP", clientIp);
		map.put("URL", logRequest.getUrl());
		map.put("METHOD", logRequest.getMethod());
		map.put("URL_DATA", logRequest.getUrlData());
		map.put("REQ_BODY", logRequest.getReqBody());
		map.put("REQ_DATE", logRequest.getReqDate());
		map.put("RESP_BODY", logRequest.getRespBody());
		map.put("RESP_DATE", logRequest.getRespDate());
		return map;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getComponentId() {
		return componentId;
	}
	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	public String getFuncId() {
		return funcId;
	}
	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}
	public String getFuncName() {
		return funcName;
	}
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getOperDate() {
		return operDate;
	}
	public void setOperDate(Date operDate) {
		this.operDate = operDate;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setProjectAndTenant(String projectCode, String tenantId) {
		this.projectCode = projectCode;
		this.tenantId = tenantId;
	}
	public String getInvalidToken() {
		return invalidToken;
	}
	public void setInvalidToken(String invalidToken) {
		this.invalidToken = invalidToken;
	}
	public LogRequest getLogRequest() {
		return logRequest;
	}
	public void setLogRequest(LogRequest logRequest) {
		this.logRequest = logRequest;
	}
}
