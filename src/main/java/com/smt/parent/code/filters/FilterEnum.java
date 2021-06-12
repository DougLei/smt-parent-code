package com.smt.parent.code.filters;

/**
 * 过滤器枚举
 * @author DougLei
 */
public enum FilterEnum {
	
	/**
	 * License过滤器
	 */
	LICENSE(10),
	
	/**
	 * 跨域过滤器
	 */
	CORS(20),
	
	/**
	 * 日志过滤器
	 */
	LOG(30) {
		@Override
		public String getHeaderName() {
			return "_log";
		}
	},
	
	/**
	 * token过滤器
	 */
	TOKEN(40) {
		@Override
		public String getHeaderName() {
			return "token";
		}
	};
	
	private int order;// 过滤器的顺序
	private FilterEnum(int order) {
		this.order = order;
	}
	
	/**
	 * 获取过滤器的name
	 * @return
	 */
	public String getName() {
		return "smt-filter-" + name();
	}
	/**
	 * 获取过滤器的顺序
	 * @return
	 */
	public int getOrder() {
		return order;
	}
	/**
	 * 过滤器在header中的name
	 * @return
	 */
	public String getHeaderName() {
		return null;
	}
	/**
	 * 过滤器要拦截的url数组
	 * @return
	 */
	public String[] getUrlPatterns() {
		return new String[] {"/*"};
	}
}
