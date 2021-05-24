package com.smt.parent.code.spring.eureka.cloud.feign;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpHeaders;

import com.alibaba.fastjson.JSONObject;
import com.smt.parent.code.filters.token.TokenContext;

/**
 * 通用的(同步)api服务
 * @author DougLei
 */
public abstract class APIGeneralServer extends APIServer {
	
	/**
	 * 获取API头信息
	 * @return
	 */
	public HttpHeaders getHeaders() {
		HttpHeaders header = super.getHeaders();
		try {
			header.add("token_", URLEncoder.encode(JSONObject.toJSONString(TokenContext.get()), StandardCharsets.UTF_8.name()));
		} catch (UnsupportedEncodingException e) {}
		return header;
	}
}
