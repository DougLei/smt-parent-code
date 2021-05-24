package com.smt.parent.code.filters.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import com.alibaba.fastjson.JSONObject;
import com.douglei.tools.ExceptionUtil;
import com.smt.parent.code.spring.eureka.cloud.feign.APIServer;
import com.smt.parent.code.spring.eureka.cloud.feign.RestTemplateWrapper;

/**
 * 日志持久化处理器
 * @author DougLei
 */
public class LogPersistenceHandler {
	private static final Logger logger= LoggerFactory.getLogger(LogPersistenceHandler.class);
	
	@Autowired
	private RestTemplateWrapper restTemplate;

	@Async
	void save(LogOperation log) {
		try {
			restTemplate.exchange(new APIServer() {
				
				@Override
				public String getName() {
					return "(异步)保存日志";
				}
				
				@Override
				public String getUrl() {
					return "http://smt-log/smt-log/log/add_";
				}
				
			}, JSONObject.toJSONString(log), null);
		} catch (Exception e) {
			logger.error("(异步)保存日志异常: {}", ExceptionUtil.getStackTrace(e));
		}
	}
}
