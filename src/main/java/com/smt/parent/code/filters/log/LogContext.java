package com.smt.parent.code.filters.log;

import com.smt.parent.code.response.Response;

/**
 * 
 * @author DougLei
 */
public class LogContext {
	private static final ThreadLocal<LogOperation> LOG_CONTEXT = new ThreadLocal<LogOperation>();
	
	/**
	 * 设置操作日志实例
	 * @param log
	 */
	static void set(LogOperation log) {
		LOG_CONTEXT.set(log);
	}
	
	/**
	 * 移除并返回操作日志实例
	 * @return
	 */
	static LogOperation remove() {
		LogOperation log = LOG_CONTEXT.get();
		if(log != null)
			LOG_CONTEXT.remove();
		return log;
	}
	
	/**
	 * 记录请求操作的用户id
	 * @param userId
	 */
	public static void loggingUserId(String userId) {
		LogOperation log = LOG_CONTEXT.get();
		if(log == null)
			return;
		
		log.setUserId(userId);
	}
	
	/**
	 * 记录请求操作的响应体
	 * @param response
	 */
	public static void loggingResponse(Response response) {
		LogOperation log = LOG_CONTEXT.get();
		if(log == null)
			return;
		
		log.getLogRequest().setResponse(response);
	}
}