package com.smt.parent.code.response;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletResponse;

/**
 * 响应体工具类
 * @author DougLei
 */
public class ResponseUtil {
	
	/**
	 * 输出json响应体
	 * @param response
	 * @param json
	 * @throws IOException 
	 */
	public static void writeJSON(HttpServletResponse response, String json) throws IOException {
		response.setContentType("application/json;charset=utf-8");
		response.getOutputStream().write(json.getBytes(StandardCharsets.UTF_8));
	}
}
