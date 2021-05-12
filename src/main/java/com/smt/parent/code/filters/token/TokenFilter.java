package com.smt.parent.code.filters.token;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;

import com.smt.parent.code.response.Response;
import com.smt.parent.code.response.ResponseContext;
import com.smt.parent.code.response.ResponseUtil;
import com.smt.parent.code.spring.eureka.cloud.feign.APIServer;
import com.smt.parent.code.spring.eureka.cloud.feign.RestTemplateWrapper;

/**
 * token验证过滤器
 * @author DougLei
 */
public class TokenFilter implements Filter {
	
	@Autowired
	private TokenConfigurationProperties properties;
	
	@Autowired
	private RestTemplateWrapper restTemplate;

	@Override
	public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		if((properties.getIgnoreUrlMatcher() == null || !properties.getIgnoreUrlMatcher().match(request.getServletPath())) 
				&& !validate(request.getHeader("token"), (HttpServletResponse)response)) 
			return;
		chain.doFilter(req, response);
	}
	
	/**
	 * 验证token
	 * @param token
	 * @param resp
	 * @return 验证是否成功
	 * @throws IOException 
	 */
	private boolean validate(String token, HttpServletResponse resp) throws IOException {
//		TokenValidateResult result = restTemplate.exchange(new APIServer() {
//			
//			@Override
//			public String getName() {
//				return "(同步)验证Token API";
//			}
//			
//			@Override
//			public String getUrl() {
//				return "http://smt-base/token/validate/" + token;
//			}
//
//			@Override
//			public HttpMethod getRequestMethod() {
//				return HttpMethod.GET;
//			}
//
//		}, null, TokenValidateResult.class).getBody();
//		
//		// 验证成功, 则记录token数据, 并继续
//		if(result.isSuccess()) {
//			TokenContext.set(result.getEntity());
//			return true;
//		}
//		
//		// 验证失败, 输出失败的具体信息
//		ResponseContext.get().addValidation(null, null, result.getMessage(), result.getCode(), result.getParams());
//		Response response = ResponseContext.getAndRemove();
//		ResponseUtil.writeJSON(resp, response.toJSONString());
		return true;
	}
}