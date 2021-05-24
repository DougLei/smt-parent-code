package com.smt.parent.code.query.mode.impl;

import java.util.List;

import com.douglei.orm.context.SessionContext;
import com.douglei.orm.sessionfactory.sessions.session.sqlquery.impl.AbstractParameter;
import com.smt.parent.code.query.mode.Mode;

/**
 * 
 * @author DougLei
 */
public class CountQueryMode implements Mode {
	
	@Override
	public Long executeQuery(String name, Object sqlParameter, List<AbstractParameter> parameters) {
		return SessionContext.getSQLQuerySession().countQuery(name, sqlParameter, parameters);
	}
}
