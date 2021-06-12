package com.smt.parent.code.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.smt.parent.code.filters.cors.CorsFilter;
import com.smt.parent.code.filters.license.LicenseFilter;
import com.smt.parent.code.filters.log.LogFilter;
import com.smt.parent.code.filters.log.LogPersistenceHandler;
import com.smt.parent.code.filters.token.TokenConfigurationProperties;
import com.smt.parent.code.filters.token.TokenFilter;
import com.smt.parent.code.spring.eureka.cloud.feign.RestTemplateWrapper;

/**
 * 
 * @author DougLei
 */
@Configuration
public class FilterRegistry {
	
	/**
	 * 注册license过滤器, 必须注册
	 * @return
	 */
	@Bean
	public FilterRegistrationBean<LicenseFilter> licenseFilter(){
		FilterRegistrationBean<LicenseFilter> registration = new FilterRegistrationBean<LicenseFilter>();
		registration.setFilter(licenseFilterBean());
		registration.setName(FilterEnum.LICENSE.getName());
		registration.addUrlPatterns(FilterEnum.LICENSE.getUrlPatterns());
		registration.setOrder(FilterEnum.LICENSE.getOrder());
		return registration;
	}
	@Bean
	public LicenseFilter licenseFilterBean() {
		return new LicenseFilter();
	}
	
	// -----------------------------------------------------------------------------------------------
	/**
	 * 注册cors过滤器, 必须注册
	 * @return
	 */
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		FilterRegistrationBean<CorsFilter> registration = new FilterRegistrationBean<CorsFilter>();
		registration.setFilter(coreFilterBean());
		registration.setName(FilterEnum.CORS.getName());
		registration.addUrlPatterns(FilterEnum.CORS.getUrlPatterns());
		registration.setOrder(FilterEnum.CORS.getOrder());
		return registration;
	}
	@Bean
	public CorsFilter coreFilterBean() {
		return new CorsFilter();
	}
	
	// -----------------------------------------------------------------------------------------------
	/**
	 * 注册log过滤器, 默认注册
	 * @param properties
	 * @return
	 */
	@Bean
	@ConditionalOnExpression("${smt.parent.code.filter.log.enable:true}")
	public FilterRegistrationBean<LogFilter> logFilter(){
		FilterRegistrationBean<LogFilter> registration = new FilterRegistrationBean<LogFilter>();
		registration.setFilter(logFilterBean());
		registration.setName(FilterEnum.LOG.getName());
		registration.addUrlPatterns(FilterEnum.LOG.getUrlPatterns());
		registration.setOrder(FilterEnum.LOG.getOrder());
		return registration;
	}
	@Bean
	@ConditionalOnExpression("${smt.parent.code.filter.log.enable:true}")
	public LogFilter logFilterBean() {
		return new LogFilter();
	}
	@Bean
	@ConditionalOnExpression("${smt.parent.code.filter.log.enable:true}")
	public LogPersistenceHandler logPersistenceHandler() {
		return new LogPersistenceHandler();
	}
	
	// -----------------------------------------------------------------------------------------------
	/**
	 * 注册token验证过滤器, 默认注册 
	 * @param properties
	 * @return
	 */
	@Bean
	@ConditionalOnExpression("${smt.parent.code.filter.token.enable:true}")
	public FilterRegistrationBean<TokenFilter> tokenFilter(@Autowired TokenConfigurationProperties properties, @Autowired RestTemplateWrapper restTemplate){
		FilterRegistrationBean<TokenFilter> registration = new FilterRegistrationBean<TokenFilter>();
		registration.setFilter(tokenFilterBean());
		registration.setName(FilterEnum.TOKEN.getName());
		registration.addUrlPatterns(FilterEnum.TOKEN.getUrlPatterns());
		registration.setOrder(FilterEnum.TOKEN.getOrder());
		return registration;
	}
	@Bean
	@ConditionalOnExpression("${smt.parent.code.filter.token.enable:true}")
	public TokenFilter tokenFilterBean() {
		return new TokenFilter();
	}
}
