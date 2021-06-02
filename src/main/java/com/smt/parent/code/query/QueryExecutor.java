package com.smt.parent.code.query;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.douglei.orm.context.PropagationBehavior;
import com.douglei.orm.context.Transaction;
import com.douglei.orm.context.TransactionComponent;
import com.smt.parent.code.response.Response;

/**
 * 
 * @author DougLei
 */
@TransactionComponent
public class QueryExecutor {
	
	@Autowired
	private QueryCriteriaResolver queryCriteriaResolver;
	
	/**
	 * 执行查询
	 * @param name
	 * @param sqlParameter
	 * @param request
	 * @param excludeParameter 从Request.Parameter中要排除的参数名数组
	 * @return
	 */
	@Transaction(propagationBehavior=PropagationBehavior.SUPPORTS)
	public Response execute(String name, Object sqlParameter, HttpServletRequest request, String... excludeParameter) {
		QueryCriteriaEntity entity = parse(request, excludeParameter);
		return new Response(entity.getMode().executeQuery(name, sqlParameter, entity.getParameters()));
	}
	
	/**
	 * 执行查询
	 * @param name
	 * @param sqlParameter
	 * @param entity
	 * @return
	 */
	@Transaction(propagationBehavior=PropagationBehavior.SUPPORTS)
	public Response execute(String name, Object sqlParameter, QueryCriteriaEntity entity) {
		return new Response(entity.getMode().executeQuery(name, sqlParameter, entity.getParameters()));
	} 
	
	/**
	 * 解析获取QueryCriteriaEntity
	 * @param request
	 * @param excludeParameter
	 * @return
	 */
	public QueryCriteriaEntity parse(HttpServletRequest request, String... excludeParameter) {
		return queryCriteriaResolver.parse(extractUrlParams(request, excludeParameter));
	}
	
	// 提取url参数
	private Map<String, Object> extractUrlParams(HttpServletRequest request, String... excludeParameter){
		Map<String, Object> urlParams = new HashMap<String, Object>(16);
		
		Enumeration<String> parameterNames = request.getParameterNames();
		if(parameterNames != null && parameterNames.hasMoreElements()){
			String key = null;
			top: 
			while(parameterNames.hasMoreElements()){
				key = parameterNames.nextElement();// 获取key
				for(String exclude: excludeParameter)
					if(key.equals(exclude))
						continue top;
				
				urlParams.put(key, request.getParameter(key).trim());
			}
		}
		return urlParams;
	}
}
