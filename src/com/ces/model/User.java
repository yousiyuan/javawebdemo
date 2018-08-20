package com.ces.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * javaBean：java简单对象对应着数据库中的一张表
 */
public class User {
	
	public User() {
		super();
	}
	
	private Integer userId;
	private String userName;
	private String passWord;
	private Integer gender;
	private Date birthday;
	private String address;
	private BigDecimal balance;
	private Date createTime;
	private Integer status;
	private String picture;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(birthday);
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public String getCreateTime() {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(createTime);
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getPicture(){
		return picture;
	}
	public void setPicture(String picture){
		this.picture = picture;
	}
	
	@Override
	public String toString() {
		return "Users [userId=" + userId + ", userName=" + userName + ", passWord=" + passWord + ", gender=" + gender
				+ ", birthday=" + this.getBirthday() + ", address=" + address + ", balance=" + balance + ", createTime="
				+ this.getCreateTime() + ", status=" + status + "]";
	}
}
