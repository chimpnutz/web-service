package com.pc2mweb.model;

public class ChangePasswordModel {
	
	String username;
	String newpass;
	String newpass2;
	String oldpass;
	String message;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNewpass() {
		return newpass;
	}
	public void setNewpass(String newpass) {
		this.newpass = newpass;
	}
	public String getOldpass() {
		return oldpass;
	}
	public void setOldpass(String oldpass) {
		this.oldpass = oldpass;
	}
	public String getNewpass2() {
		return newpass2;
	}
	public void setNewpass2(String newpass2) {
		this.newpass2 = newpass2;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}


}
