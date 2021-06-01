package com.smt.parent.code.query;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import com.douglei.orm.context.PropagationBehavior;
import com.douglei.orm.context.Transaction;
import com.douglei.orm.context.TransactionComponent;
import com.douglei.orm.sessionfactory.sessions.session.sqlquery.impl.AbstractParameter;
import com.douglei.orm.sessionfactory.sessions.session.sqlquery.impl.Operator;
import com.douglei.orm.sessionfactory.sessions.session.sqlquery.impl.Parameter;
import com.douglei.orm.sessionfactory.sessions.sqlsession.PageRecursiveEntity;
import com.douglei.orm.sessionfactory.sessions.sqlsession.RecursiveEntity;
import com.douglei.tools.StringUtil;
import com.smt.parent.code.query.mode.Mode;
import com.smt.parent.code.query.mode.impl.CountQueryMode;
import com.smt.parent.code.query.mode.impl.LimitQueryMode;
import com.smt.parent.code.query.mode.impl.PageQueryMode;
import com.smt.parent.code.query.mode.impl.PageRecursiveQueryMode;
import com.smt.parent.code.query.mode.impl.QueryMode;
import com.smt.parent.code.query.mode.impl.RecursiveQueryMode;
import com.smt.parent.code.query.mode.impl.UniqueQueryMode;
import com.smt.parent.code.response.Response;

/**
 * 
 * @author DougLei
 */
@TransactionComponent
public class QueryExecutor {
	
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
	
	// 解析获取QueryCriteriaEntity
	private QueryCriteriaEntity parse(HttpServletRequest request, String... excludeParameter) {
		Map<String, String> urlParams = extractUrlParams(request, excludeParameter);
		String mode = urlParams.remove("$mode$").toUpperCase();
		
		if("QUERY".equals(mode))
			return build(new QueryMode(), urlParams);
		
		if("UNIQUE_QUERY".equals(mode))
			return build(new UniqueQueryMode(), urlParams);
		
		if("LIMIT_QUERY".equals(mode))
			return build(new LimitQueryMode(Integer.parseInt(urlParams.remove("_startRow")), Integer.parseInt(urlParams.remove("_length"))), urlParams);
		
		if("COUNT_QUERY".equals(mode))
			return build(new CountQueryMode(), urlParams);
		
		if("PAGE_QUERY".equals(mode))
			return build(new PageQueryMode(Integer.parseInt(urlParams.remove("_page")), Integer.parseInt(urlParams.remove("_rows"))), urlParams);
		
		if("RECURSIVE_QUERY".equals(mode)) {
			RecursiveEntity entity = new RecursiveEntity();
			setPropertyValues(urlParams, entity);
			return build(new RecursiveQueryMode(entity), urlParams);
		}
		
		if("PAGE_RECURSIVE_QUERY".equals(mode)) {
			PageRecursiveEntity entity = new PageRecursiveEntity(Integer.parseInt(urlParams.remove("_page")), Integer.parseInt(urlParams.remove("_rows")));
			setPropertyValues(urlParams, entity);
			return build(new PageRecursiveQueryMode(entity), urlParams);
		}
		
		throw new IllegalArgumentException("目前不支持["+mode+"]的查询方式");
	}
	
	// 提取url参数
	private Map<String, String> extractUrlParams(HttpServletRequest request, String... excludeParameter){
		Map<String, String> urlParams = new HashMap<String, String>(16);
		
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
	
	// 设置entity的属性值
	private void setPropertyValues(Map<String, String> urlParams, RecursiveEntity entity) {
		urlParams.remove("_recursive");
		
		String deep = urlParams.remove("_deep");
		if(deep != null)
			entity.setDeep(Integer.parseInt(deep));
		
		String column = urlParams.remove("$column$");
		if(StringUtil.unEmpty(column))
			entity.setColumn(column);
		
		String parentColumn = urlParams.remove("_pcName");
		if(StringUtil.unEmpty(parentColumn))
			entity.setParentColumn(parentColumn);
		
		String children = urlParams.remove("$children$");
		if(StringUtil.unEmpty(children))
			entity.setChildren(children);
		
		
		// TODO 尝试从_root中获取筛选根的条件, 目前只能筛选出parent_id的条件值, 其他筛选条件抛弃, 后期完善这块的功能
		List<String> temp = new ArrayList<String>(urlParams.size());
		for(Entry<String, String> entry: urlParams.entrySet()) {
			if(!entry.getKey().startsWith("_root."))
				continue;
				
			temp.add(entry.getKey());
			if(entry.getKey().equals("_root."+entity.getParentColumn()))
				entity.setValue(entry.getValue().split(","));
		}
		if(temp.size() > 0) 
			temp.forEach(t -> urlParams.remove(t));
	}
	
	// 构建QueryCriteriaEntity实例
	private QueryCriteriaEntity build(Mode mode, Map<String, String> urlParams) {
		List<AbstractParameter> parameters = null;
		
		// 提取出排序
		String sorts = urlParams.remove("_sort");
		if(sorts != null) {
			if(parameters == null)
				parameters = new ArrayList<AbstractParameter>();
			
			for(String sort: sorts.split(",")) {
				String[] array = sort.trim().split(" ");
				if(array.length==1)
					parameters.add(new Parameter(false, Operator.ORDER, array[0]));
				else
					parameters.add(new Parameter(false, Operator.ORDER, array[0], array[1]));
			}
		}
		
		// 解析其他
		for(Entry<String, String> entry : urlParams.entrySet()) {
			if(parameters == null)
				parameters = new ArrayList<AbstractParameter>();
			parameters.add(getParameter(entry.getKey(), entry.getValue()));
		}
		return new QueryCriteriaEntity(mode, parameters);
	}

	// 获取Parameter实例
	private AbstractParameter getParameter(String name, String value) {
		int commaIndex = name.indexOf(',');
		if(commaIndex != -1)
			name = name.substring(0, commaIndex);
		
		int splitIndex = 0;
		for(; splitIndex < value.length(); splitIndex++) {
			if(value.charAt(splitIndex) == '(')
				break;
		}
		
		if(splitIndex == value.length())
			return new Parameter(false, Operator.EQ, name, value);
		
		String val = value.substring(splitIndex+1, value.length()-1);
		if(val.equalsIgnoreCase("null"))
			return new Parameter(false, Operator.valueOf(value.substring(0, splitIndex).toUpperCase()), name);
		
		Object[] values = val.split(",");
		return new Parameter(false, Operator.valueOf(value.substring(0, splitIndex).toUpperCase()), name, values);
	}
}
