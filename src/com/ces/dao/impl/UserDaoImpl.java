package com.ces.dao.impl;

import java.math.BigDecimal;
import java.sql.JDBCType;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.lnson.util.JdbcSqlParameter;
import org.lnson.util.JdbcUtils;

import com.ces.dao.UserDao;
import com.ces.model.User;

public class UserDaoImpl implements UserDao {

	@Override
	public int saveUser(User user) {
		String sql = "INSERT INTO T_USERS(USER_ID, USER_NAME, PASSWORD, GENDER, BIRTHDAY, ADDRESS, BALANCE, CREATETIME, STATUS, PICTURE)VALUES(SEQ_TAB_USER_ID.NEXTVAL, ?, ?, ?, ?, ?, ?, SYSDATE, 1, ?)";
		List<JdbcSqlParameter> parameters = new ArrayList<JdbcSqlParameter>();
		parameters.add(new JdbcSqlParameter(user.getUserName(), JDBCType.NVARCHAR));
		parameters.add(new JdbcSqlParameter(user.getPassWord(), JDBCType.NVARCHAR));
		parameters.add(new JdbcSqlParameter(user.getGender(), JDBCType.NUMERIC));
		parameters.add(new JdbcSqlParameter(user.getBirthday(), JDBCType.DATE));
		parameters.add(new JdbcSqlParameter(user.getAddress(), JDBCType.NVARCHAR));
		parameters.add(new JdbcSqlParameter(user.getBalance(), JDBCType.NUMERIC, 2));
		parameters.add(new JdbcSqlParameter(user.getPicture(), JDBCType.NVARCHAR));

		JdbcUtils jdbc = JdbcUtils.getJdbc();
		int row = jdbc.insert(sql, parameters);
		jdbc.dispose();
		return row;
	}

	@Override
	public int updateUser(User user) {
		String sql = "UPDATE T_USERS SET USER_NAME = ?, PASSWORD = ?, GENDER = ?, BIRTHDAY = ?, ADDRESS = ?, BALANCE = ?, PICTURE = ? WHERE USER_ID = ?";
		List<JdbcSqlParameter> parameters = new ArrayList<JdbcSqlParameter>();
		parameters.add(new JdbcSqlParameter(user.getUserName(), JDBCType.NVARCHAR));
		parameters.add(new JdbcSqlParameter(user.getPassWord(), JDBCType.NVARCHAR));
		parameters.add(new JdbcSqlParameter(user.getGender(), JDBCType.NUMERIC));
		parameters.add(new JdbcSqlParameter(user.getBirthday(), JDBCType.DATE));
		parameters.add(new JdbcSqlParameter(user.getAddress(), JDBCType.NVARCHAR));
		parameters.add(new JdbcSqlParameter(user.getBalance(), JDBCType.NUMERIC, 2));
		parameters.add(new JdbcSqlParameter(user.getPicture(), JDBCType.NVARCHAR));
		parameters.add(new JdbcSqlParameter(user.getUserId(), JDBCType.NUMERIC));

		JdbcUtils jdbc = JdbcUtils.getJdbc();
		int row = jdbc.insert(sql, parameters);
		jdbc.dispose();
		return row;
	}

	@Override
	public int deleteUser(Integer id) {
		String sql = "DELETE FROM T_USERS WHERE USER_ID = ?";
		List<JdbcSqlParameter> parameters = new ArrayList<JdbcSqlParameter>();
		parameters.add(new JdbcSqlParameter(id, JDBCType.NUMERIC));

		JdbcUtils jdbc = JdbcUtils.getJdbc();
		int row = jdbc.delete(sql, parameters);
		jdbc.dispose();
		return row;
	}

	@Override
	public User queryUser(Integer id) {
		String sql = "SELECT * FROM T_USERS WHERE USER_ID = ?";
		List<JdbcSqlParameter> parameters = new ArrayList<JdbcSqlParameter>();
		parameters.add(new JdbcSqlParameter(id, JDBCType.NUMERIC));

		JdbcUtils jdbc = JdbcUtils.getJdbc();
		LinkedHashMap<String, Object> entity = jdbc.queryForObject(sql, parameters);
		jdbc.dispose();

		Integer userId = jdbc.cast(Integer.class, entity.get("USER_ID"));
		String userName = jdbc.cast(String.class, entity.get("USER_NAME"));
		String passWord = jdbc.cast(String.class, entity.get("PASSWORD"));
		Integer gender = jdbc.cast(Integer.class, entity.get("GENDER"));
		java.util.Date birthday = jdbc.cast(java.util.Date.class, entity.get("BIRTHDAY"));
		String address = jdbc.cast(String.class, entity.get("ADDRESS"));
		BigDecimal balance = jdbc.cast(BigDecimal.class, entity.get("BALANCE"));
		java.util.Date createTime = jdbc.cast(java.util.Date.class, entity.get("CREATETIME"));
		Integer status = jdbc.cast(Integer.class, entity.get("STATUS"));
		String picture = jdbc.cast(String.class, entity.get("PICTURE"));

		User user = new User();
		user.setUserId(userId);
		user.setUserName(userName);
		user.setPassWord(passWord);
		user.setGender(gender);
		user.setBirthday(birthday);
		user.setAddress(address);
		user.setBalance(balance);
		user.setCreateTime(createTime);
		user.setStatus(status);
		user.setPicture(picture);

		return user;
	}

	@Override
	public List<User> queryUserList() {
		List<User> users = new ArrayList<User>();
		User user = null;

		String sql = "SELECT * FROM T_USERS ORDER BY USER_ID ASC";
		ArrayList<JdbcSqlParameter> paramlist = new ArrayList<JdbcSqlParameter>();

		// 获取数据库连接
		JdbcUtils jdbc = JdbcUtils.getJdbc();
		List<LinkedHashMap<String, Object>> entitylist = jdbc.queryForList(sql, paramlist);
		// 释放资源
		jdbc.dispose();
		for (LinkedHashMap<String, Object> entity : entitylist) {
			Integer userId = jdbc.cast(Integer.class, entity.get("USER_ID"));
			String userName = jdbc.cast(String.class, entity.get("USER_NAME"));
			String passWord = jdbc.cast(String.class, entity.get("PASSWORD"));
			Integer gender = jdbc.cast(Integer.class, entity.get("GENDER"));
			java.util.Date birthday = jdbc.cast(java.util.Date.class, entity.get("BIRTHDAY"));
			String address = jdbc.cast(String.class, entity.get("ADDRESS"));
			BigDecimal balance = jdbc.cast(BigDecimal.class, entity.get("BALANCE"));
			java.util.Date createTime = jdbc.cast(java.util.Date.class, entity.get("CREATETIME"));
			Integer status = jdbc.cast(Integer.class, entity.get("STATUS"));
			String picture = jdbc.cast(String.class, entity.get("PICTURE"));

			user = new User();
			user.setUserId(userId);
			user.setUserName(userName);
			user.setPassWord(passWord);
			user.setGender(gender);
			user.setBirthday(birthday);
			user.setAddress(address);
			user.setBalance(balance);
			user.setCreateTime(createTime);
			user.setStatus(status);
			user.setPicture(picture);
			users.add(user);
		}

		return users;
	}

	@Override
	public User login(String name, String password) {
		String sql = "SELECT * FROM T_USERS T WHERE T.USER_NAME = ? AND T.PASSWORD = ?";
		List<JdbcSqlParameter> parameters = new ArrayList<JdbcSqlParameter>();
		parameters.add(new JdbcSqlParameter(name, JDBCType.NVARCHAR));
		parameters.add(new JdbcSqlParameter(password, JDBCType.NVARCHAR));
		// 获取数据库连接
		JdbcUtils jdbc = JdbcUtils.getJdbc();
		LinkedHashMap<String, Object> entity = jdbc.queryForObject(sql, parameters);
		// 释放资源
		jdbc.dispose();
		
		Integer userId = jdbc.cast(Integer.class, entity.get("USER_ID"));
		String userName = jdbc.cast(String.class, entity.get("USER_NAME"));
		String passWord = jdbc.cast(String.class, entity.get("PASSWORD"));
		Integer gender = jdbc.cast(Integer.class, entity.get("GENDER"));
		java.util.Date birthday = jdbc.cast(java.util.Date.class, entity.get("BIRTHDAY"));
		String address = jdbc.cast(String.class, entity.get("ADDRESS"));
		BigDecimal balance = jdbc.cast(BigDecimal.class, entity.get("BALANCE"));
		java.util.Date createTime = jdbc.cast(java.util.Date.class, entity.get("CREATETIME"));
		Integer status = jdbc.cast(Integer.class, entity.get("STATUS"));

		User user = new User();
		user.setUserId(userId);
		user.setUserName(userName);
		user.setPassWord(passWord);
		user.setGender(gender);
		user.setBirthday(birthday);
		user.setAddress(address);
		user.setBalance(balance);
		user.setCreateTime(createTime);
		user.setStatus(status);
		
		return user;
	}
}
