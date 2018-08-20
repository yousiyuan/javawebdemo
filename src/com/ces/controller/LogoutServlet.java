package com.ces.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {

	private static final long serialVersionUID = -2990919268466996282L;

	public LogoutServlet() {
		super();
	}

	@Override
	public void init() throws ServletException {
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		// 销毁当前Session会话对象
		session.invalidate();
		// 退出到登录页面
		response.sendRedirect(request.getContextPath() + "/login.jsp");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	@Override
	public void destroy() {
		super.destroy();
	}

}
