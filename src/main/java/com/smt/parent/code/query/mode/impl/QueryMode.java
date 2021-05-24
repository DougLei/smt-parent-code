package com.smt.parent.code.query.mode.impl;

import java.util.List;
import java.util.Map;

import com.douglei.orm.context.SessionContext;
import com.douglei.orm.sessionfactory.sessions.session.sqlquery.impl.AbstractParameter;
import com.smt.parent.code.query.mode.Mode;

/**
 * 
 * @author DougLei
 */
public class QueryMode implements Mode {

	@Override
	public List<Map<String, Object>> executeQuery(String name, Object sqlParameter, List<AbstractParameter> parameters) {
		return SessionContext.getSQLQuerySession().query(name, sqlParameter, parameters);
	}
}
