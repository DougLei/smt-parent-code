package com.smt.parent.code.response.data;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.douglei.tools.ExceptionUtil;

/**
 * 
 * @author DougLei
 */
public class ErrorData extends Data{
	private static final Logger logger = LoggerFactory.getLogger(ErrorData.class);
	
	public ErrorData(Object data, Exception exception) {
		super(data, "操作异常, 联系管理员查看日志, exceptionId=[%s]", "smt.op.data.exception", UUID.randomUUID());
		logger.error("操作异常, exceptionId=[{}], exceptionDetail=\n{}", getParams()[0], ExceptionUtil.getStackTrace(exception));
	}
}
