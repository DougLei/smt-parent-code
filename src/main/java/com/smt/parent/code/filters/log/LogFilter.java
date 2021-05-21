package com.smt.parent.code.filters.log;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.douglei.tools.web.HttpUtil;
import com.smt.parent.code.filters.FilterEnum;

/**
 * 
 * @author DougLei
 */
public class LogFilter implements Filter{
	
	@Autowired
	private LogConfigurationProperties properties;
	
	@Autowired
	private LogPersistenceHandler handler;

	@Override
	public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = new HttpServletRequest4Log((HttpServletRequest) req);
		boolean success = build(request);
		chain.doFilter(request, response);
		if(success)
			save();
	}
	
	/**
	 * 构建操作日志实例
	 * @param request
	 * @return 是否构建成功
	 * @throws IOException
	 */
	private boolean build(HttpServletRequest request) throws IOException {
		if(properties.getIgnoreUrlMatcher() != null && properties.getIgnoreUrlMatcher().match(request.getServletPath()))
			return false;
		
		String str = request.getHeader(FilterEnum.LOG.getHeaderName());
		if(str == null) 
			return false;
		
		JSONObject json = JSONObject.parseObject(URLDecoder.decode(str, StandardCharsets.UTF_8.name()));
		if(json.isEmpty())
			return false;
		
		LogOperation log = json.toJavaObject(LogOperation.class);
		log.setOperDate(new Date());
		log.setClientIp(HttpUtil.getClientIp(request));
		log.setLogRequest(extractRequestLog(request, log));
		
		LogContext.set(log);
		return true;
	}
	
	/**
	 * 提取请求日志
	 * @param request
	 * @param operation
	 * @return
	 * @throws IOException 
	 */
	private LogRequest extractRequestLog(HttpServletRequest request, LogOperation operation) throws IOException {
		LogRequest logRequest = new LogRequest();
		logRequest.setApiAddr(request.getServletPath());
		logRequest.setMethod(request.getMethod());
		logRequest.setUrlData(request.getQueryString() == null?null:URLDecoder.decode(request.getQueryString(), "utf-8"));
		logRequest.setReqBody(extractRequestBody(request));
		logRequest.setReqDate(operation.getOperDate());
		return logRequest;
	}

	// 提取请求体
	private String extractRequestBody(HttpServletRequest request) throws IOException {
		if(request instanceof HttpServletRequest4Log) 
			return ((HttpServletRequest4Log)request).getRequestBody2String();
		return HttpUtil.getRequestBody2String(request);
	}
	
	/**
	 * 保存操作日志
	 * @param operation
	 */
	private void save() {
		LogOperation log = LogContext.remove();
		if(log == null)
			return;
		
		handler.save(log);
	}
}
