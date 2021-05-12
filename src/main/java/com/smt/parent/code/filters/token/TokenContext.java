package com.smt.parent.code.filters.token;

/**
 * 
 * @author DougLei
 */
public class TokenContext {
	private static final ThreadLocal<TokenEntity> CONTEXT = new ThreadLocal<TokenEntity>();
	
	/**
	 * 设置Token数据实例
	 * @param entity
	 */
	static void set(TokenEntity entity) {
		CONTEXT.set(entity);
	}
	
	/**
	 * 获取Token数据实例
	 * @return
	 */
	public static TokenEntity get() {
		return CONTEXT.get();
	}
}
