package com.smt.parent.code.filters.log;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 
 * @author DougLei
 */
public class HttpServletRequest4Log extends HttpServletRequestWrapper {
	private byte[] body;
	
	public HttpServletRequest4Log(HttpServletRequest request) throws IOException {
		super(request);
		String contentType = request.getContentType();
		if(contentType != null && contentType.startsWith("multipart/")) 
			return; // 说明是上传文件, 直接结束
		
		if(request.getContentLength() > 0) {
			body = new byte[request.getContentLength()];
			try(InputStream in = request.getInputStream()) {
				int index = 0;
				int len = 2048;
				while(index < body.length && (len = in.read(body, index, len)) != -1) 
					index += len;
			}
		}else {
			body = new byte[0];
		}
	}
	
	@Override
	public ServletInputStream getInputStream() throws IOException {
		return new ServletInputStream4Log(body);
	}
	
	/**
	 * 
	 * @author DougLei
	 */
	private class ServletInputStream4Log extends ServletInputStream {
		private final ByteArrayInputStream bais; 
		public ServletInputStream4Log(byte[] body) {
			this.bais = new ByteArrayInputStream(body);
		}

		@Override
		public boolean isFinished() {
			return false;
		}

		@Override
		public boolean isReady() {
			return false;
		}

		@Override
		public void setReadListener(ReadListener listener) {
		}

		@Override
		public int read() throws IOException {
			return bais.read();
		}
	}


	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(body)));
	}
	
	/**
	 * 获取请求体字符串
	 * @return
	 */
	public String getRequestBody2String() {
		if(body == null || body.length == 0) 
			return null;
		return new String(body, StandardCharsets.UTF_8).trim();
	}
}
