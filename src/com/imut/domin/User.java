package com.imut.domin;

public class User {
	private int userId;
	private String userName;
	private String userSex;
	private int userAge;
	private String userNum;
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String userName, String userSex, int userAge, String userNum) {
		super();
		this.userName = userName;
		this.userSex = userSex;
		this.userAge = userAge;
		this.userNum = userNum;
	}

	public User(int userId, String userName, String userSex, int userAge, String userNum) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userSex = userSex;
		this.userAge = userAge;
		this.userNum = userNum;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	public String getUserNum() {
		return userNum;
	}
	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}
	
	
	
	
}
