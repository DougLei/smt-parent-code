package com.smt.parent.code.filters.log;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.douglei.tools.web.UrlMatcher;

/**
 * 
 * @author DougLei
 */
@Component
@ConfigurationProperties(prefix="smt.parent.code.filter.log")
public class LogConfigurationProperties {
	private boolean enable=true; // 是否启用过滤器, 默认为true
	private UrlMatcher ignoreUrlMatcher;// 忽略记录日志的url匹配器

	/**
	 * 获取忽略记录日志的url匹配器
	 * @return
	 */
	public UrlMatcher getIgnoreUrlMatcher() {
		return ignoreUrlMatcher;
	}
	
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public String[] getIgnoreUrlPatterns() {
		return null;
	}
	public void setIgnoreUrlPatterns(String[] ignoreUrlPatterns) {
		this.ignoreUrlMatcher = new UrlMatcher(ignoreUrlPatterns);
	}
}
