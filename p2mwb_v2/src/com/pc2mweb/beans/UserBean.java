package com.pc2mweb.beans;


import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;


public class UserBean implements Serializable{



	//    private BigDecimal   UserID;
	private int   UserID;
    private String UserName;
    private String Password;
    private String UserLevel;
    private String LastName;
    private String FirstName;
    private String MiddleName;
	public String aid;
	public String pid;
	public String bid;
	public String type;
	public int walletid;
	public String runmode;
	public String paymenttype;
	public String partnername;
	



    public UserBean(int   userBean,String userName,String password,String userlevel,String lastName,String firstName,String middleName){

        this.UserID = userBean;
        this.UserName = userName;
        this.Password = password;
        this.UserLevel = userlevel;
        this.LastName = lastName;
        this.FirstName = firstName;
        this.MiddleName = middleName;


    }
    

	public UserBean(){

    }

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


    public String getLastName() {
        return LastName;
    }


    public void setLastName(String LastName) {
        this.LastName = LastName;
    }


    public String getFirstName() {
        return FirstName;
    }


    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }


    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String MiddleName) {
        this.MiddleName = MiddleName;
    }

    /**
     * @return the UserLevel
     */
    public String getUserLevel() {
        return UserLevel;
    }

    /**
     * @param UserLevel the UserLevel to set
     */
    public void setUserLevel(String UserLevel) {
    	this.UserLevel = UserLevel;
    }
    



	
    public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public int getWalletid() {
		return walletid;
	}


	public void setWalletid(int walletid) {
		this.walletid = walletid;
	}


	public String getRunmode() {
		return runmode;
	}


	public void setRunmode(String runmode) {
		this.runmode = runmode;
	}


	public String getPaymenttype() {
		return paymenttype;
	}


	public void setPaymenttype(String paymenttype) {
		this.paymenttype = paymenttype;
	}


	public String getPartnername() {
		return partnername;
	}


	public void setPartnername(String partnername) {
		this.partnername = partnername;
	}



}