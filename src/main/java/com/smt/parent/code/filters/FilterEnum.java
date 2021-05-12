package com.smt.parent.code.filters;

/**
 * 过滤器枚举
 * @author DougLei
 */
public enum FilterEnum {
	
	/**
	 * 跨域过滤器
	 */
	CORS(10, new String[] {"/*"}),
	
	/**
	 * 日志过滤器
	 */
	LOG(20, new String[] {"/*"}),
	
	/**
	 * token过滤器
	 */
	TOKEN(30, new String[] {"/*"});
	

	private String name;// 过滤器的name
	private byte order;// 过滤器的顺序
	private String[] urlPatterns;// 过滤器要拦截的url数组

	private FilterEnum(int order, String[] urlPatterns) {
		this.name = "smt-filter-" + name();
		this.order = (byte)order;
		this.urlPatterns = urlPatterns;
	}
	
	public String getName() {
		return name;
	}
	public byte getOrder() {
		return order;
	}
	public String[] getUrlPatterns() {
		return urlPatterns;
	}
}
