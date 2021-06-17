package com.smt.parent.code.filters.token;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 
 * @author DougLei
 */
public class TokenEntity implements Serializable{
	private String value;// token值
	private int accountId;// 账户id
	private String userId; // 用户id
	private String userName; // 用户name
	private List<String> orgs; // 所在的组织机构code
	private List<String> posts; // 所拥有的岗位code
	private List<String> roles; // 所拥有的角色code
	private String projectCode; // 当前所在的项目code
	private List<String> parentProjectCodes; // 当前项目的所有父项目code
	private String tenantId;// 租户id
	private Date loginDate;// 登陆时间
	private int clientType; // 客户端类型
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
	public String getUserName() {
		return userName;
	}
	public List<String> getOrgs() {
		return orgs;
	}
	public List<String> getPosts() {
		return posts;
	}
	public List<String> getRoles() {
		return roles;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public List<String> getParentProjectCodes() {
		return parentProjectCodes;
	}
	public String getTenantId() {
		return tenantId;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public int getClientType() {
		return clientType;
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
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setOrgs(List<String> orgs) {
		this.orgs = orgs;
	}
	public void setPosts(List<String> posts) {
		this.posts = posts;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public void setParentProjectCodes(List<String> parentProjectCodes) {
		this.parentProjectCodes = parentProjectCodes;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	public void setClientType(int clientType) {
		this.clientType = clientType;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public void setExtend(Map<String, Object> extend) {
		this.extend = extend;
	}
	
	// -------------------------------------------------------------------------------------------------
	private transient Date currentDate; // 当前时间
	private transient String uuid; // uuid
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
