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

import com.douglei.tools.web.HttpUtil;
import com.smt.parent.code.filters.FilterEnum;
import com.smt.parent.code.filters.log.LogContext;
import com.smt.parent.code.response.Response;
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
				&& !validate(request, (HttpServletResponse)response)) 
			return;
		chain.doFilter(req, response);
		TokenContext.remove();
	}
	
	/**
	 * 验证token
	 * @param req
	 * @param resp
	 * @return 
	 * @throws IOException 
	 */
	private boolean validate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String token = req.getHeader(FilterEnum.TOKEN.getHeaderName());
		TokenValidateResult result = restTemplate.exchange(new APIServer() {
			
			@Override
			public String getName() {
				return "(同步)验证Token API";
			}
			
			@Override
			public String getUrl() {
				return "http://smt-base/token/validate/" + token + "?clientIp=" + HttpUtil.getClientIp(req);
			}
			
			@Override
			public HttpMethod getRequestMethod() {
				return HttpMethod.GET;
			}

		}, null, TokenValidateResult.class).getBody();
		
		// 在日志中记录请求操作的用户id
		if(result.getEntity() != null) 
			LogContext.loggingUserId(result.getEntity().getUserId());
			
		// 验证成功, 则记录token数据, 并继续
		if(result.isSuccess()) {
			TokenContext.set(result.getEntity());
			return true;
		}
		
		// 验证失败, 输出失败的具体信息, 并记录日志
		Response response = new Response(token, null, result.getMessage(), result.getCode());
		LogContext.loggingResponse(response);
		ResponseUtil.writeJSON(resp, response.toJSONString());
		return false;
	}
}