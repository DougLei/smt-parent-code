package com.smt.parent.code.filters.token;

import java.util.Date;
import java.util.UUID;

/**
 * 
 * @author DougLei
 */
public class TokenEntity {
	private String value;// token值
	private String accountId;// 账户id
	private String userId; // 用户id
	private String projectCode;// 项目code
	private String tenantId;// 租户id
	private Date loginDate;// 登陆时间
	private String clientIp;// 登陆的客户端ip
	private Date lastOpDate;// 最后操作时间
	private int loginFailTimes;// (连续)登录失败的次数
	private Date currentDate; // 当前时间
	private String uuid; // uuid
	
	public String getValue() {
		return value;
	}
	public String getAccountId() {
		return accountId;
	}
	public String getUserId() {
		return userId;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public String getTenantId() {
		return tenantId;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public String getClientIp() {
		return clientIp;
	}
	public Date getLastOpDate() {
		return lastOpDate;
	}
	public int getLoginFailTimes() {
		return loginFailTimes;
	}
	public Date getCurrentDate() {
		if(currentDate == null)
			currentDate = new Date();
		return currentDate;
	}
	public Date getCurrentDate(boolean cache) { // cache是否使用缓存数据
		if(cache)
			return getCurrentDate();
		return new Date();
	}
	public String getUUID() {
		if(uuid == null)
			uuid = UUID.randomUUID().toString();
		return uuid;
	}
	public String getUUID(boolean cache) {  // cache是否使用缓存数据
		if(cache)
			return getUUID();
		return UUID.randomUUID().toString();
	}
	
	// -------------------------------------------------------------------------------------------------
	public void setValue(String value) {
		this.value = value;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public void setLastOpDate(Date lastOpDate) {
		this.lastOpDate = lastOpDate;
	}
	public void setLoginFailTimes(int loginFailTimes) {
		this.loginFailTimes = loginFailTimes;
	}
}
