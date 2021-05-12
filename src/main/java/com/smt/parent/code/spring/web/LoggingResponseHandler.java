package com.smt.parent.code.spring.web;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.smt.parent.code.filters.log.LogContext;
import com.smt.parent.code.response.Response;

/**
 * 
 * @author DougLei
 */
@RestControllerAdvice
public class LoggingResponseHandler implements ResponseBodyAdvice<Response> {

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return returnType.hasMethodAnnotation(LoggingResponse.class);
	}

	@Override
	public Response beforeBodyWrite(Response body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		
		LogContext.loggingResponse(body);
		return body;
	}
}
