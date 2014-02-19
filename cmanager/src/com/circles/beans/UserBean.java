package com.circles.beans;

public class UserBean {

	private int   UserID;
    private String Username;
    private String Password;
    private String UserLevel;
	public String email;
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
	public String getUserName() {
		return Username;
	}
	public void setUserName(String userName) {
		Username = userName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getUserLevel() {
		return UserLevel;
	}
	public void setUserLevel(String userLevel) {
		UserLevel = userLevel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
