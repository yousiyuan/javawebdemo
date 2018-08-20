package com.ces.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ces.model.User;

public class LoginFilter implements Filter {

	private static List<String> ignorePages;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);
		ignorePages = Arrays.asList(filterConfig.getInitParameter("IgnorePage").split("\\|"));
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 强转为HttpServletRequest
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		String url = req.getRequestURI();
		for (String page : ignorePages) {
			if (url.indexOf(page) > -1) {
				chain.doFilter(req, resp);
				return;
			}
		}

		// 获取Session
		HttpSession session = req.getSession();
		// 从会话域对象中获取登录信息
		User user = (User) session.getAttribute("loginfo");
		if (user == null) {
			// 跳转
			resp.sendRedirect(req.getContextPath() + "/login.jsp");
		} else {
			chain.doFilter(req, resp);
		}
	}

	@Override
	public void destroy() {
		Filter.super.destroy();
	}

}
