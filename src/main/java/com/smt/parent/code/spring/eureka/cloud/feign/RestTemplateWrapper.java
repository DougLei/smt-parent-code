package com.smt.parent.code.spring.eureka.cloud.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.smt.parent.code.response.State;

/**
 * 
 * @author DougLei
 */
public class RestTemplateWrapper {
	private static final Logger logger = LoggerFactory.getLogger(RestTemplateWrapper.class);
	private RestTemplate restTemplate;

	public RestTemplateWrapper(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	/**
	 * 
	 * @param server
	 * @param requestBody
	 * @param responseType
	 * @param uriVariables
	 * @return
	 */
	public <T> ResponseEntity<T> exchange(APIServer api, Object requestBody, Class<T> responseType, Object... uriVariables) {
		logger.debug("调用api: {}", api);
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody, api.getHeaders());
		return restTemplate.exchange(api.getUrl(), api.getRequestMethod(), requestEntity, responseType, uriVariables);
	}
	
	/**
	 * 
	 * @param server
	 * @param requestBody
	 * @param uriVariables
	 * @return
	 */
	public Object generalExchange(APIGeneralServer api, Object requestBody, Object... uriVariables) {
		try {
			// 发起请求, 获取响应数据
			String result= exchange(api, requestBody, String.class, uriVariables).getBody();
			
			// 解析响应数据, 并返回响应体中的正确数据
			APIGeneralResponse response = JSONObject.parseObject(result, APIGeneralResponse.class);
			if(response.getState() != State.SUCCESS)
				throw new APIException("调用["+api.getName()+"]API时, 响应结果不成功: "+ result);
			return response.getData();
		} catch (Exception e) {
			if(e instanceof APIException)
				throw e;
			throw new APIException("调用["+api.getName()+"]API时出现异常", e);
		}
	}
}
