package com.ces.dao;

import java.util.List;

import com.ces.model.User;

public interface UserDao {
	public int saveUser(User user);

	public int updateUser(User user);

	public abstract int deleteUser(Integer id);

	public User queryUser(Integer id);

	public List<User> queryUserList();

	public default User login(String name, String password) {
		return new User();
	}
}
