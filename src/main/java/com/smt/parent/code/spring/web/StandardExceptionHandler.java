package com.smt.parent.code.spring.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.douglei.tools.ExceptionUtil;
import com.smt.parent.code.filters.log.LogContext;
import com.smt.parent.code.response.Response;
import com.smt.parent.code.response.ResponseContext;
import com.smt.parent.code.response.ResponseUtil;

/**
 * 
 * @author DougLei
 */
@Configuration
public class StandardExceptionHandler implements HandlerExceptionResolver{
	private static final Logger logger = LoggerFactory.getLogger(StandardExceptionHandler.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest req, HttpServletResponse resp, Object handler, Exception ex) {
		try {
			ResponseContext.clear();
			
			Response response = new Response(ex);
			LogContext.loggingResponse(response);
			ResponseUtil.writeJSON(resp, response.toJSONString());
		} catch (IOException e) {
			logger.error("向调用端输出异常响应体时出现错误: {}", ExceptionUtil.getStackTrace(e));
		}
		return new ModelAndView();
	}
}
