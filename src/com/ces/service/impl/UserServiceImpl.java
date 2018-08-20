package com.ces.service.impl;

import java.util.List;

import com.ces.dao.UserDao;
import com.ces.dao.impl.UserDaoImpl;
import com.ces.model.User;
import com.ces.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao _userDao = new UserDaoImpl();
	// private UserDao _userDao = new UserDaoForMySqlImpl();

	@Override
	public int saveUser(User user) {
		return _userDao.saveUser(user);
	}

	@Override
	public int updateUser(User user) {
		return _userDao.updateUser(user);
	}

	@Override
	public int deleteUser(Integer id) {
		return _userDao.deleteUser(id);
	}

	@Override
	public User queryUser(Integer id) {
		return _userDao.queryUser(id);
	}

	@Override
	public List<User> queryUserList() {
		return _userDao.queryUserList();
	}

	@Override
	public User login(String username, String password) {
		return _userDao.login(username, password);
	}
}
