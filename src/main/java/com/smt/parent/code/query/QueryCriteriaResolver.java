package com.smt.parent.code.query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.alibaba.fastjson.JSONObject;
import com.douglei.orm.sessionfactory.sessions.session.sqlquery.impl.AbstractParameter;
import com.douglei.orm.sessionfactory.sessions.session.sqlquery.impl.Operator;
import com.douglei.orm.sessionfactory.sessions.session.sqlquery.impl.Parameter;
import com.douglei.orm.sessionfactory.sessions.sqlsession.PageRecursiveEntity;
import com.douglei.orm.sessionfactory.sessions.sqlsession.RecursiveEntity;
import com.smt.parent.code.filters.log.HttpServletRequest4Log;
import com.smt.parent.code.query.mode.Mode;
import com.smt.parent.code.query.mode.impl.CountQueryMode;
import com.smt.parent.code.query.mode.impl.LimitQueryMode;
import com.smt.parent.code.query.mode.impl.PageQueryMode;
import com.smt.parent.code.query.mode.impl.PageRecursiveQueryMode;
import com.smt.parent.code.query.mode.impl.QueryMode;
import com.smt.parent.code.query.mode.impl.RecursiveQueryMode;
import com.smt.parent.code.query.mode.impl.UniqueQueryMode;

/**
 * 
 * @author DougLei
 */
public class QueryCriteriaResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		  return parameter.getParameterType() == QueryCriteriaEntity.class && parameter.hasParameterAnnotation(QueryCriteria.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		return parse(JSONObject.parseObject(((HttpServletRequest4Log)webRequest.getNativeRequest(HttpServletRequest.class)).getRequestBody2String()));
	}
	
	/**
	 * ????????????QueryCriteriaEntity
	 * @param json
	 * @return
	 */
	public QueryCriteriaEntity parse(Map<String, Object> json) {
		Object mode = json.remove("$mode$");
		if(mode == null)
			throw new IllegalArgumentException("????????????$mode$??????????????????");
		
		mode = mode.toString().toUpperCase();
		if("QUERY".equals(mode))
			return build(new QueryMode(), json);
		
		if("UNIQUE_QUERY".equals(mode))
			return build(new UniqueQueryMode(), json);
		
		if("LIMIT_QUERY".equals(mode))
			return build(new LimitQueryMode(Integer.parseInt(json.remove("_startRow").toString()), Integer.parseInt(json.remove("_length").toString())), json);
		
		if("COUNT_QUERY".equals(mode))
			return build(new CountQueryMode(), json);
		
		if("PAGE_QUERY".equals(mode))
			return build(new PageQueryMode(Integer.parseInt(json.remove("_page").toString()), Integer.parseInt(json.remove("_rows").toString())), json);
		
		if("RECURSIVE_QUERY".equals(mode)) {
			RecursiveEntity entity = new RecursiveEntity();
			setPropertyValues(json, entity);
			return build(new RecursiveQueryMode(entity), json);
		}
		
		if("PAGE_RECURSIVE_QUERY".equals(mode)) {
			PageRecursiveEntity entity = new PageRecursiveEntity(Integer.parseInt(json.remove("_page").toString()), Integer.parseInt(json.remove("_rows").toString()));
			setPropertyValues(json, entity);
			return build(new PageRecursiveQueryMode(entity), json);
		}
		
		throw new IllegalArgumentException("???????????????["+mode+"]???????????????");
	}
	
	// ??????entity????????????
	private void setPropertyValues(Map<String, Object> json, RecursiveEntity entity) {
		json.remove("_recursive");
		
		Object deep = json.remove("_deep");
		if(deep != null)
			entity.setDeep(Integer.parseInt(deep.toString()));
		
		Object column = json.remove("$column$");
		if(column != null)
			entity.setColumn(column.toString());
		
		Object parentColumn = json.remove("_pcName");
		if(parentColumn != null)
			entity.setParentColumn(parentColumn.toString());
		
		Object children = json.remove("$children$");
		if(children != null)
			entity.setChildren(children.toString());
		
		
		// TODO ?????????_root???????????????????????????, ?????????????????????parent_id????????????, ????????????????????????, ???????????????????????????
		List<String> temp = new ArrayList<String>(json.size());
		for(Entry<String, Object> entry: json.entrySet()) {
			if(!entry.getKey().startsWith("_root."))
				continue;
				
			temp.add(entry.getKey());
			if(entry.getKey().equals("_root."+entity.getParentColumn()) && entry.getValue() != null)
				entity.setValue(entry.getValue().toString().split(","));
		}
		if(temp.size() > 0) 
			temp.forEach(t -> json.remove(t));
	}
	
	// ??????QueryCriteriaEntity??????
	private QueryCriteriaEntity build(Mode mode, Map<String, Object> json) {
		List<AbstractParameter> parameters = null;
		
		// ???????????????
		Object sorts = json.remove("_sort");
		if(sorts != null) {
			if(parameters == null)
				parameters = new ArrayList<AbstractParameter>();
			
			for(String sort: sorts.toString().split(",")) {
				String[] array = sort.trim().split(" ");
				if(array.length==1)
					parameters.add(new Parameter(false, Operator.ORDER, array[0]));
				else
					parameters.add(new Parameter(false, Operator.ORDER, array[0], array[1]));
			}
		}
		
		// ????????????
		for(Entry<String, Object> entry : json.entrySet()) {
			if(parameters == null)
				parameters = new ArrayList<AbstractParameter>();
			parameters.add(getParameter(entry.getKey(), entry.getValue().toString()));
		}
		return new QueryCriteriaEntity(mode, parameters);
	}

	// ??????Parameter??????
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
