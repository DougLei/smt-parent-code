package com.smt.parent.code.response.data;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.douglei.tools.ExceptionUtil;

/**
 * 
 * @author DougLei
 */
public class ExceptionData extends Data {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionData.class);
	
	public ExceptionData(Exception exception) {
		super(null, "系统异常, 联系管理员查看日志, exceptionId=[%s]", "smt.parent.system.exception", UUID.randomUUID().toString());
		logger.error("系统异常, exceptionId=[{}], exceptionDetail=\n{}", getParams()[0], ExceptionUtil.getStackTrace(exception));
	}
}
