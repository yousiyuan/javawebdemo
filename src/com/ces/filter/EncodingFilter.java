package com.ces.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class EncodingFilter implements Filter {

	private static String encoding;

	public EncodingFilter() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);

		encoding = filterConfig.getInitParameter("encoder").trim();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// 强制转换ServletRequest为HttpServletRequest
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		// 获得请求方式
		String method = req.getMethod();
		if ("get".equalsIgnoreCase(method)) {

			// 创建装饰器类
			req = encoding == null || "".equals(encoding.trim()) ? new CustomHttpServletRequest(req)
					: new CustomHttpServletRequest(req, encoding);
		} else if ("post".equalsIgnoreCase(method)) {

			// 处理Post请求时乱码问题
			req.setCharacterEncoding(encoding);

			// Post方式响应页面乱码处理
			resp.setCharacterEncoding(encoding);
		}

		chain.doFilter(req, resp);
	}

	@Override
	public void destroy() {
	}

	/**
	 * 重写HTTPServletRequest获取参数的函数
	 */
	private class CustomHttpServletRequest extends HttpServletRequestWrapper {

		private final static String DEFAULT_ENCODING = "UTF-8";
		private String encoder;
		private HttpServletRequest request;

		public CustomHttpServletRequest(HttpServletRequest request) {
			this(request, DEFAULT_ENCODING);
		}

		public CustomHttpServletRequest(HttpServletRequest request, String encoder) {
			super(request);
			this.request = request;
			this.encoder = encoder;
		}

		@Override
		public String getParameter(String name) {
			String[] values = getParameterValues(name);
			if (values.length > 0)
				return values[0];
			return null;
		}

		@Override
		public String[] getParameterValues(String name) {
			Map<String, String[]> map = getParameterMap();
			return map.get(name);
		}

		@Override
		public Map<String, String[]> getParameterMap() {
			Map<String, String[]> map = new HashMap<String, String[]>();
			try {
				map = decodingParameter();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return map;
		}

		private Map<String, String[]> decodingParameter() throws UnsupportedEncodingException {
			Map<String, String[]> resultMap = new HashMap<String, String[]>();
			Map<String, String[]> map = request.getParameterMap();
			Iterator<Entry<String, String[]>> iter = map.entrySet().iterator();
			while (iter.hasNext()) {
				Entry<String, String[]> entry = iter.next();
				String name = entry.getKey();
				String[] values = entry.getValue();
				for (int i = 0; i < values.length; i++) {
					// 判断是否UTF-8编码，不是就转为UTF-8编码
					if (!(Charset.forName(encoder).newEncoder().canEncode(values[i]))) {
						values[i] = new String(values[i].getBytes(Charset.forName("ISO-8859-1")), encoder);
					}
				}
				resultMap.put(name, values);
			}
			return resultMap;
		}
	}
}
