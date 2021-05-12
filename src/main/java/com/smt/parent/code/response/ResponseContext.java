package com.smt.parent.code.response;

import com.smt.parent.code.filters.log.LogContext;

/**
 * 
 * @author DougLei
 */
public class ResponseContext {
	private static final ThreadLocal<Response> CONTEXT = new ThreadLocal<Response>();
	
	/**
	 * 获取响应体; 如果不存在则会初始化响应体并返回
	 * @return
	 */
	public static Response get() {
		Response response = CONTEXT.get();
		if(response == null) {
			response = new Response();
			CONTEXT.set(response);
		}
		return response;
	}
	
	/**
	 * 获取并移除响应体; 如果不存在响应体则会抛出异常
	 * @return
	 */
	public static Response getAndRemove() {
		return getAndRemove(false);
	}
	
	/**
	 * 获取并移除响应体; 如果不存在响应体则会抛出异常
	 * @param isBatch 是否批量操作数据
	 * @return
	 */
	public static Response getAndRemove(boolean isBatch) {
		Response response = CONTEXT.get();
		if(response == null) 
			throw new NullPointerException("response body is null, can not remove");
		CONTEXT.remove();
		
		response.setBatch(isBatch);
		LogContext.loggingResponse(response);
		return response;
	}
	
	/**
	 * 清理数据; 如果存在则清楚, 否则什么都不做
	 */
	public static void clear() {
		if(CONTEXT.get() != null)
			CONTEXT.remove();
	}
}
