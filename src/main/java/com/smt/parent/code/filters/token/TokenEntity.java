package com.smt.parent.code.filters.token;

import java.util.Date;

/**
 * 
 * @author DougLei
 */
public class TokenEntity {
	private Date currentDate = new Date();
	private String token;// token
	private String accountId;// 账户id
	private String userId; // 用户id
	private String projectCode;// 项目code
	private String tenantId;// 租户id
	private Date loginDate;// 登陆时间
	private String clientIp;// 登陆的客户端ip
	private Date lastOpDate;// 最后操作时间
	private int loginFailTimes;// (连续)登录失败的次数
	
	public Date getCurrentDate() {
		return currentDate;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public Date getLastOpDate() {
		return lastOpDate;
	}
	public void setLastOpDate(Date lastOpDate) {
		this.lastOpDate = lastOpDate;
	}
	public int getLoginFailTimes() {
		return loginFailTimes;
	}
	public void setLoginFailTimes(int loginFailTimes) {
		this.loginFailTimes = loginFailTimes;
	}
}
