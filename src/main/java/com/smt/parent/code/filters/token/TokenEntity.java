package com.smt.parent.code.filters.token;

import java.util.Date;

/**
 * 
 * @author DougLei
 */
public class TokenEntity {
	private String token;// token
	private String accountId;// 账户id
	private String userId; // 用户id
	private String projectId;// 项目id
	private String tenantId;// 租户id
	private Date loginDate;// 登陆时间
	private String clientIp;// 登陆的客户端ip
	private Date lastOpDate;// 最后操作时间
	private int loginFailTimes;// (连续)登录失败的次数
	
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
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
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
