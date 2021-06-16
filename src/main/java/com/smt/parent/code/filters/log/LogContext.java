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
	 * 记录日志关联用户/项目/租户的唯一标识
	 * @param userId
	 * @param projectCode
	 * @param tenantId
	 */
	public static void loggingIds(String userId, String projectCode, String tenantId) {
		LogOperation log = LOG_CONTEXT.get();
		if(log == null)
			return;
		
		log.setUserId(userId);
		log.setProjectAndTenant(projectCode, tenantId);
	}
	
	/**
	 * 记录请求操作的响应信息
	 * @param response
	 * @param loggingBody 是否记录响应体
	 */
	public static void loggingResponse(Response response, boolean loggingBody) {
		LogOperation log = LOG_CONTEXT.get();
		if(log == null)
			return;
		
		log.getLogRequest().setResponse(response, loggingBody);
	}
}