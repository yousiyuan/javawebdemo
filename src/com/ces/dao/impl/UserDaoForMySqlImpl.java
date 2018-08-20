package com.ces.dao.impl;

import java.util.List;

import com.ces.dao.UserDao;
import com.ces.model.User;

public class UserDaoForMySqlImpl implements UserDao {

	@Override
	public int saveUser(User user) {
		return 0;
	}

	@Override
	public int updateUser(User user) {
		return 0;
	}

	@Override
	public int deleteUser(Integer id) {
		return 0;
	}

	@Override
	public User queryUser(Integer id) {
		return null;
	}

	@Override
	public List<User> queryUserList() {
		return null;
	}

}
