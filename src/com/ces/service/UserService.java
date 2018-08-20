package com.ces.service;

import java.util.List;

import com.ces.model.User;

public interface UserService {

	public int saveUser(User user);

	public int updateUser(User user);

	public abstract int deleteUser(Integer id);

	public User queryUser(Integer id);

	public List<User> queryUserList();

	public default User login(String username, String password) {
		return new User();
	}
}
