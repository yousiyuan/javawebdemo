package com.ces.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ces.model.User;
import com.ces.service.UserService;
import com.ces.service.impl.UserServiceImpl;

public class QueryServlet extends HttpServlet {

	private static final long serialVersionUID = -4938358888370516663L;

	public QueryServlet() {
		System.out.println("QueryServlet构造函数");
	}

	@Override
	public void init() throws ServletException {
		System.out.println("QueryServlet对象被创建");
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService _userService = new UserServiceImpl();
		List<User> users = _userService.queryUserList();
		
		request.setAttribute("userList", users);
		request.getRequestDispatcher("/tab/tab.jsp").forward(request, response);
	}

	@Override
	public void destroy() {
		super.destroy();
		System.out.println("QueryServlet对象被销毁");
	}

}
