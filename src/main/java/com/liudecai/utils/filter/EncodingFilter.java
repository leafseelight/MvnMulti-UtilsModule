package com.liudecai.utils.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 全站编码过滤，统一设置成UTF-8
 * 
 * @author liudecai
 * @email 1911939348@qq.com
 */
public class EncodingFilter implements Filter {
	private String charset = "UTF-8";

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		if (req.getMethod().equalsIgnoreCase("GET")) {
			if (!(req instanceof GetRequest)) {
				req = new GetRequest(req, charset);// 处理get请求编码
			}
		} else {
			req.setCharacterEncoding(charset);// 处理post请求编码
		}
		chain.doFilter(req, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		String charset = fConfig.getInitParameter("charset");
		if (charset != null && !charset.isEmpty()) {
			this.charset = charset;
		}
	}
}
