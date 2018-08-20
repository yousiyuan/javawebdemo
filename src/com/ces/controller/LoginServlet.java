package com.ces.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ces.model.User;
import com.ces.service.UserService;
import com.ces.service.impl.UserServiceImpl;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -2003077554099898402L;
	private static UserService _userService;

	public LoginServlet() {
		_userService = new UserServiceImpl();
	}

	@Override
	public void init() throws ServletException {
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取登录参数
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		User user = _userService.login(username, password);
		if (user != null) {
			// 获取Session对象
			HttpSession session = req.getSession();
			// 把登录用户信息放入HttpSession域对象中
			session.setAttribute("loginfo", user);
			// 跳转到主页
			resp.sendRedirect(req.getContextPath() + "/main.html");
		} else {
			resp.setContentType("text/html;charset=utf-8");
			resp.getWriter().write("<font color='red'>用户名或者密码错误</font>");
		}
	}

	@Override
	public void destroy() {

	}
}
