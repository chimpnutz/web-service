package com.elp.beans;


import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;


public class UserBean implements Serializable{



	//    private BigDecimal   UserID;
	private int   UserID;
	private int levelid;
	private int roleid;
    private String UserName;
    private String Password;
    private String rolename;
    private String companyname;




    public int  getUserID() {
        return UserID;
    }


    public void setUserID(int   UserID) {
        this.UserID = UserID;
    }


    public String getUserName() {
        return UserName;
    }


    public void setUserName(String UserName) {
        this.UserName = UserName;
    }


    public String getPassword() {
        return Password;
    }


    public void setPassword(String Password) {
        this.Password = Password;
    }


	public String getRolename() {
		return rolename;
	}


	public void setRolename(String rolename) {
		this.rolename = rolename;
	}


	public String getCompanyname() {
		return companyname;
	}


	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}


	public int getLevelid() {
		return levelid;
	}


	public void setLevelid(int levelid) {
		this.levelid = levelid;
	}


	public int getRoleid() {
		return roleid;
	}


	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}






}