package com.smt.parent.code.filters.token;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * 
 * @author DougLei
 */
public class TokenEntity {
	private String value;// token值
	private int accountId;// 账户id
	private String userId; // 用户id
	private String[] orgs; // 所在的组织机构code
	private String[] roles; // 所拥有的角色code
	private String[] posts; // 所拥有的岗位code
	private String projectCode; // 当前所在的项目code
	private String tenantId;// 租户id
	private Date loginDate;// 登陆时间
	private Date lastOpDate;// 最后操作时间
	private String clientIp;// 登陆的客户端ip
	private Map<String, Object> extend; // 扩展信息
	
	public String getValue() {
		return value;
	}
	public int getAccountId() {
		return accountId;
	}
	public String getUserId() {
		return userId;
	}
	public String[] getOrgs() {
		return orgs;
	}
	public String[] getRoles() {
		return roles;
	}
	public String[] getPosts() {
		return posts;
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
	public Date getLastOpDate() {
		return lastOpDate;
	}
	public String getClientIp() {
		return clientIp;
	}
	public Map<String, Object> getExtend() {
		return extend;
	}
	
	// -------------------------------------------------------------------------------------------------
	public void setValue(String value) {
		this.value = value;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setOrgs(String[] orgs) {
		this.orgs = orgs;
	}
	public void setRoles(String[] roles) {
		this.roles = roles;
	}
	public void setPosts(String[] posts) {
		this.posts = posts;
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
	public void setLastOpDate(Date lastOpDate) {
		this.lastOpDate = lastOpDate;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public void setExtend(Map<String, Object> extend) {
		this.extend = extend;
	}
	
	// -------------------------------------------------------------------------------------------------
	private Date currentDate; // 当前时间
	private String uuid; // uuid
	public Date getCurrentDate() {
		if(currentDate == null)
			currentDate = new Date();
		return currentDate;
	}
	public String getUUID() {
		if(uuid == null)
			uuid = UUID.randomUUID().toString();
		return uuid;
	}
}
