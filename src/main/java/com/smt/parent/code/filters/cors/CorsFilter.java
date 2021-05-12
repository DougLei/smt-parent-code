package com.smt.parent.code.filters.cors;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author DougLei
 */
public class CorsFilter implements Filter{
	
	@Autowired
	private CorsConfigurationProperties properties;
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		if(properties.getAccessControlAllowOrigin() != null) 
			((HttpServletResponse) resp).setHeader("Access-Control-Allow-Origin", properties.getAccessControlAllowOrigin());
		
		if(properties.getAccessControlAllowMethods() != null)
			((HttpServletResponse) resp).setHeader("Access-Control-Allow-Methods", properties.getAccessControlAllowMethods());  
		
		if(properties.getAccessControlAllowHeaders() != null)
			((HttpServletResponse) resp).setHeader("Access-Control-Allow-Headers", properties.getAccessControlAllowHeaders());
		
		if(properties.isAccessControlAllowCredentials())
			((HttpServletResponse) resp).setHeader("Access-Control-Allow-Credentials", "true"); // 允许cookie
		
		if(properties.getAccessControlMaxAge() != null)
			((HttpServletResponse) resp).setHeader("Access-Control-Max-Age", properties.getAccessControlMaxAge()); 
		
		if("OPTIONS".equalsIgnoreCase(((HttpServletRequest)req).getMethod()))
			return;
		
		chain.doFilter(req, resp);
	}
}
