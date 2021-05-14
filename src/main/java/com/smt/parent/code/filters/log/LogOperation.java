package com.smt.parent.code.filters.log;

import java.util.Date;

/**
 * 
 * @author DougLei
 */
public class LogOperation {
	private int id;
	private String batch;
	private int level;
	private String description;
	private String moduleId;
	private String moduleName;
	private String componentId;
	private String componentName;
	private String funcId;
	private String funcName;
	private int clientType;
	private String clientInstanceName;
	private String clientIp;
	private String clientMAC;
	private String userId;
	private Date operDate;
	private LogRequest logRequest;
	
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
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
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
	public int getClientType() {
		return clientType;
	}
	public void setClientType(int clientType) {
		this.clientType = clientType;
	}
	public String getClientInstanceName() {
		return clientInstanceName;
	}
	public void setClientInstanceName(String clientInstanceName) {
		this.clientInstanceName = clientInstanceName;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getClientMAC() {
		return clientMAC;
	}
	public void setClientMAC(String clientMAC) {
		this.clientMAC = clientMAC;
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
	public LogRequest getLogRequest() {
		return logRequest;
	}
	public void setLogRequest(LogRequest logRequest) {
		this.logRequest = logRequest;
	}
}
