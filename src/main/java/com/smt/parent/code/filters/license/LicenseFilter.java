package com.smt.parent.code.filters.license;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.douglei.mini.license.client.LicenseValidateApp;
import com.douglei.mini.license.client.ValidateResult;
import com.smt.parent.code.response.Response;
import com.smt.parent.code.response.ResponseUtil;

/**
 * 
 * @author DougLei
 */
public class LicenseFilter implements Filter{
	private LicenseValidateApp app;
	
	@Autowired
	private LicenseConfigurationProperties properties;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.app = new LicenseValidateApp(properties.getLicenseFilepath(), properties.getPublicKey());
		this.app.startup();
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		ValidateResult result = this.app.getResult();
		if(result != null) {
			ResponseUtil.writeJSON((HttpServletResponse)resp, new Response(null, null, result.getMessage(), result.getCode(), result.getParams()).toJSONString());
			return;
		}
		chain.doFilter(req, resp);
	}
}
