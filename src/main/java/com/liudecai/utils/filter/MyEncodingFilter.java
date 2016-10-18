package com.liudecai.utils.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 继承EncodingFilter处理get和post的乱码问题。
 * @author liudecai
 * @email 1911939348@qq.com

 */
public class MyEncodingFilter extends EncodingFilter implements Filter {
	private String charset = "UTF-8";
    public MyEncodingFilter() {
        super();
    }
	public void destroy() {}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		if(req.getMethod().equalsIgnoreCase("GET")) {
			if(!(req instanceof GetRequest)) {
				req = new GetRequest(req, charset);//处理get请求编码
//				resp.setCharacterEncoding(charset);
				resp.setContentType("text/html;charset=UTF-8");
			}
		} else {
			req.setCharacterEncoding(charset);//处理post请求编码
//			resp.setCharacterEncoding(charset);
			resp.setContentType("text/html;charset=UTF-8");
		}
		chain.doFilter(req, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		String charset = fConfig.getInitParameter("charset");
		if(charset != null && !charset.isEmpty()) {
			this.charset = charset;
		}
	}

}
