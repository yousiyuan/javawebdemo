package com.ces.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ces.service.UserService;
import com.ces.service.impl.UserServiceImpl;

public class DeleteServlet extends HttpServlet {

	private static final long serialVersionUID = 8806787920754815279L;

	public DeleteServlet() {
		System.out.println("DeleteServlet构造函数");
	}

	@Override
	public void init() throws ServletException {
		System.out.println("DeleteServlet对象被创建");
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("id");

		UserService _userService  = new UserServiceImpl();
		int row = _userService.deleteUser(Integer.valueOf(userid));
		if (row >= 1) {
			response.sendRedirect(request.getContextPath() + "/main.html");
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	@Override
	public void destroy() {
		System.out.println("DeleteServlet对象被销毁");
	}
	
}
